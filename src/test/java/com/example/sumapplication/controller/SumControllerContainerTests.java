package com.example.sumapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SumControllerContainerTests {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Container
    private final PostgreSQLContainer postgresqlContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:12")
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("admin")
            .withInitScript("schema.sql");

    @Test
    void test() {
        assertTrue(postgresqlContainer.isRunning());
    }
}

