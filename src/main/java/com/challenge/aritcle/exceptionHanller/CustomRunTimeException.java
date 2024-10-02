package com.challenge.aritcle.exceptionHanller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomRunTimeException extends RuntimeException {
    private ErrorCode errorCode;
    public CustomRunTimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
