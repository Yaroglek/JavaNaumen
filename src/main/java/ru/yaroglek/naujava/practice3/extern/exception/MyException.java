package ru.yaroglek.naujava.practice3.extern.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyException
{
    private String message;
    private MyException(String message)
    {
        this.message = message;
    }

    public static MyException create(Throwable e)
    {
        return new MyException(e.getMessage());
    }

    public static MyException create(String message)
    {
        return new MyException(message);
    }
}
