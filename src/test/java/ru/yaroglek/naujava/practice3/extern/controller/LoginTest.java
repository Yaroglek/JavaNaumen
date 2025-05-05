package ru.yaroglek.naujava.practice3.extern.controller;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.yaroglek.naujava.practice3.app.service.UserService;
import ru.yaroglek.naujava.practice3.domain.User;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ComponentScan("ru.yaroglek.naujava.practice3.app")
public class LoginTest {
    @Autowired
    private UserService userService;

    private WebDriver driver;

    private final String username = "test";
    private final String password = "test";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();

        User toSave = User.builder()
                .username(username)
                .password(password)
                .email("test@test.test")
                .role(User.Role.ADMIN)
                .build();

        userService.saveUser(toSave);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
        userService.deleteUserById(1L);
    }

    @Test
    public void testLoginSuccess() {
        driver.get("http://localhost:8080/login");

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        //Задержка для проверки перенаправления
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("http://localhost:8080/", driver.getCurrentUrl());
    }

    @Test
    public void testLogout() {
        testLoginSuccess();

        driver.get("http://localhost:8080/logout");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}
