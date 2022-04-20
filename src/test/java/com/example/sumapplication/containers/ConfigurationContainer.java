package com.example.sumapplication.containers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class ConfigurationContainer {

    private static final String POSTGRES_IMAGE = "postgres:12";
    private static final String DATA_BASE_NAME = "testSumApplication";
    private static final String USER_NAME = "user";
    private static final String PASSWORD_DB = "password123";
    private static final String INIT_SCRIPT = "schema.sql";

    public static PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>
            (POSTGRES_IMAGE)
            .withDatabaseName(DATA_BASE_NAME)
            .withUsername(USER_NAME)
            .withPassword(PASSWORD_DB)
            .withInitScript(INIT_SCRIPT);

    static {
        if (!postgresDB.isCreated() && !postgresDB.isRunning()) {
            postgresDB.start();
            System.out.println("Container Started!!");
        }

    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDB::getUsername);
        registry.add("spring.datasource.password", postgresDB::getPassword);

    }
}
