package com.example.budgetservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    NOT_FOUND_BUDGET(404, "NOT FOUND BUDGET", "유저의 예산이 없습니다."),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    ;

    private int status;
    private String errorCode;
    private String message;
}