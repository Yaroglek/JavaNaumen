package ru.yaroglek.naujava.practice3.database;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.yaroglek.naujava.practice3.domain.Task;

@Configuration
public class TaskDataBase
{
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, Task> userContainer()
    {
        return new HashMap<>();
    }
}
