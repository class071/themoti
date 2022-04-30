package com.daily.themoti.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode {

    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "Post not exists"),
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "Reply not exists");

    private final HttpStatus httpStatus;
    private final String message;
}
