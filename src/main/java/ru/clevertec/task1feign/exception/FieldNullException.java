package ru.clevertec.task1feign.exception;

public class FieldNullException extends RuntimeException {

    private static final String MESSAGE = "can't be null";

    public FieldNullException(Class<?> clazz) {
        super(String.format("%s: %s", clazz.getSimpleName(), MESSAGE));
    }
}