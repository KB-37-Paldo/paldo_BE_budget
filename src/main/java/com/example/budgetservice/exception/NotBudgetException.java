package com.example.budgetservice.exception;

import lombok.Getter;

@Getter
public class NotBudgetException extends RuntimeException{

    private ErrorCode errorCode;

    public NotBudgetException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}