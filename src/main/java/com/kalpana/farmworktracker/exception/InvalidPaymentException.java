package com.kalpana.farmworktracker.exception;

public class InvalidPaymentException extends RuntimeException{
	public InvalidPaymentException(String message) {
        super(message);
    }
}
