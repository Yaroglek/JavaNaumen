package ru.yaroglek.naujava.practice3.extern.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void findAllTasksByUserId_shouldReturn200AndTaskList() {
        Long testUserId = 1L;

        given()
                .queryParam("userId", testUserId)
                .when()
                .get("/custom/tasks/findAllUsersTasks")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void findAllTasksByTodoListAndDueDateExpire_shouldReturn200AndFilteredList() {
        Long listId = 1L;
        String expireDate = "2025-05-01T00:00:00";

        given()
                .queryParam("listId", listId)
                .queryParam("expireDate", expireDate)
                .when()
                .get("/custom/tasks/findExpiredTasks")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    void findAllTasksByUserId_whenInvalidId_shouldReturnEmptyList() {
        given()
                .queryParam("userId", "999999")
                .when()
                .get("/custom/tasks/findAllUsersTasks")
                .then()
                .statusCode(200)
                .body("", hasSize(0));
    }

    @Test
    void findExpiredTasks_whenInvalidDate_shouldReturn400() {
        given()
                .queryParam("listId", 1)
                .queryParam("expireDate", "invalid-date")
                .when()
                .get("/custom/tasks/findExpiredTasks")
                .then()
                .log().all()
                .statusCode(500);
    }
}
