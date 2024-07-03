package com.YS.BlokingServer.exceptions;

public class StudentByCodeNotFoundException extends RuntimeException {
    public StudentByCodeNotFoundException(String message) {
        super(message);
    }
}
