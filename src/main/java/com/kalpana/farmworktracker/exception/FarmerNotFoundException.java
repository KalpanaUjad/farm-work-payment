package com.kalpana.farmworktracker.exception;

public class FarmerNotFoundException extends RuntimeException {
    public FarmerNotFoundException(String message){
        super(message);
    }
}
