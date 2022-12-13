package com.example.payrollservice.exception;

public class EmpException extends RuntimeException{
    String message;

    public EmpException(String message) {
        this.message = message;
    }

    public EmpException(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
