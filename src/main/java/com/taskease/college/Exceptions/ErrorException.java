package com.taskease.college.Exceptions;

public class ErrorException extends RuntimeException{

    public ErrorException(String message) {
        super(message);
    }

    public ErrorException() {
        super();
    }
}
