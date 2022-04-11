package com.example.sumapplication.containers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

public abstract class ConfigurationContainer  {

    private static final String postgresImage = "postgres:12";
    private static final String dataBaseName = "testSumApplication";
    private static final String userName = "user";
    private static final String passwordDB = "password123";
    private static final String initScript = "schema.sql";

    public static PostgreSQLContainer postgresDB = (PostgreSQLContainer) new PostgreSQLContainer
            (postgresImage)
            .withDatabaseName(dataBaseName)
            .withUsername(userName)
            .withPassword(passwordDB)
            .withInitScript(initScript);

    static {
        postgresDB.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresDB::getJdbcUrl);
        registry.add("spring.datasource.username", postgresDB::getUsername);
        registry.add("spring.datasource.password", postgresDB::getPassword);

    }



}

