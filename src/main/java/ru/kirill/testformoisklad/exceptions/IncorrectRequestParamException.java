package ru.kirill.testformoisklad.exceptions;

public class IncorrectRequestParamException extends Exception{
    public IncorrectRequestParamException(String message) {
        super(message);
    }
}
