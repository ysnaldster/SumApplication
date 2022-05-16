package com.example.sumapplication.containers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public abstract class ConfigurationContainer {

    private static final String POSTGRES_IMAGE = "postgres:12";
    private static final String DATA_BASE_NAME = "testSumApplication";
    private static final String USER_NAME = "user";
    private static final String PASSWORD_DB = "password123";
    private static final String INIT_SCRIPT = "schema.sql";

    public static final PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>(POSTGRES_IMAGE).withDatabaseName(DATA_BASE_NAME).withUsername(USER_NAME).withPassword(PASSWORD_DB).withInitScript(INIT_SCRIPT).waitingFor(Wait.forLogMessage(".*Success. You can now start the database server using.*", 1));

    public static final GenericContainer redis = new GenericContainer("redis:6.2.7");

    static {
        if (!postgresDB.isRunning()) {
            postgresDB.start();
        } else {
            postgresDB.stop();
        }
        if (!redis.isRunning()) {
            redis.start();
        }
    }

    //Success. You can now start the database server using
    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgresDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDB::getUsername);
        registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
        registry.add("spring.datasource.password", postgresDB::getPassword);
    }
}
