package com.example.demo.exception;

public class NotEnoughCapacityException extends Throwable {
    public NotEnoughCapacityException(String message) {
        super(message);
    }
}
