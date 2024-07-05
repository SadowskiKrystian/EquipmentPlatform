package com.ksprogramming.equipment.exception;

public class ExpiredorUsedToken extends RuntimeException {
    public ExpiredorUsedToken(String message) {
        super(message);
    }
}
