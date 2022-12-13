package com.example.payrollservice.exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = EmpException.class)
    public String userAlreadyExist(EmpException empException) {
        return empException.getMessage();

    }
}
