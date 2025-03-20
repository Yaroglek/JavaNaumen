package ru.yaroglek.naujava.practice3.infrastructure;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@RequiredArgsConstructor
public class Config
{
    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String appVersion;

    private final CommandProcessor commandProcessor;

    @PostConstruct
    public void init() {
        System.out.println("Запущено приложение: " + appName);
        System.out.println("Версия: " + appVersion);
    }

    @Bean
    public CommandLineRunner commandScanner()
    {
        return args -> {
            try (Scanner scanner = new Scanner(System.in)) {
                printMenu();
                while (true)
                {
                    System.out.print("> ");
                    String input = scanner.nextLine().trim();

                    if ("exit".equalsIgnoreCase(input.trim()))
                    {
                        System.out.println("Выход из программы...");
                        System.exit(0);
                    }

                    commandProcessor.processCommand(input);
                }
            }
        };
    }

    private void printMenu() {
        System.out.println("\n=== TODO List ===");
        System.out.println("Создать задачу - create|{id}|{название}|{описание}|{дедлайн}");
        System.out.println("Найти задачу - get|{id}");
        System.out.println("Удалить задачу - delete|{id}");
        System.out.println("Изменить статус - status|{id}|{статус}");
        System.out.println("Изменить дату и время окончания - dueDateTime|{id}|{дата и время}");
        System.out.println("Выйти - exit");
    }
}

