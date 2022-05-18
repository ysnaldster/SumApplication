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

    private static final String REDIS_IMAGE = "redis:6.2.7";

    private static final int PORT_REDIS = 6379;

    private static final PostgreSQLContainer<?> postgresSQL = new PostgreSQLContainer<>(POSTGRES_IMAGE);
    private static final GenericContainer<?> redisContainer = new GenericContainer<>(REDIS_IMAGE);

    static {
        final int TIME_WAITING = 1;
        try (postgresSQL) {
            postgresSQL.withUsername(USER_NAME);
            postgresSQL.withPassword(PASSWORD_DB);
            postgresSQL.withInitScript(INIT_SCRIPT);
            postgresSQL.withDatabaseName(DATA_BASE_NAME);
            postgresSQL.waitingFor(Wait.forLogMessage(".*Success. You can now start the database server using.*", TIME_WAITING));
            redisContainer.withExposedPorts(PORT_REDIS);
        }
        if (!postgresSQL.isRunning()) {
            postgresSQL.start();
        } else {
            postgresSQL.stop();
        }
        if (!redisContainer.isRunning()) {
            redisContainer.start();
        }
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresSQL::getJdbcUrl);
        registry.add("spring.datasource.username", postgresSQL::getUsername);
        registry.add("spring.redis.port", () -> redisContainer.getMappedPort(PORT_REDIS));
        registry.add("spring.datasource.password", postgresSQL::getPassword);
    }
}
