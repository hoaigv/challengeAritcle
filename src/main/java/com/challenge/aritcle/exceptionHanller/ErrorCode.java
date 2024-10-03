package com.challenge.aritcle.exceptionHanller;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNAUTHENTICATED(401, "Unauthorized access. Please log in.",HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(404, "user not found !", HttpStatus.NOT_FOUND),
    PASSWORD_INCORRECT(401, "Password incorrect, please enter another password", HttpStatus.UNAUTHORIZED),
    TOKEN_CREATION_FAILED(400, "Token creation failed: bad request", HttpStatus.BAD_REQUEST),
    USER_EXISTED(400, "user already existed", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_FAILED(400, "refresh token failed: bad request", HttpStatus.BAD_REQUEST),
    URL_NOT_EXIST(404,"The requested URL does not exist." , HttpStatus.NOT_FOUND),
    FOLLOWER_EXISTED(400, "You are already following this user.",HttpStatus.BAD_REQUEST),
    FOLLOWER_NOT_EXIST(400, "You have never followed this user.",HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
