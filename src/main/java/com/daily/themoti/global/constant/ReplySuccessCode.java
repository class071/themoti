package com.daily.themoti.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReplySuccessCode {

    REPLY_CREATE_SUCCESS(HttpStatus.OK, "Reply created successfully"),
    REPLY_DELETE_SUCCESS(HttpStatus.OK, "Reply deleted successfully"),
    REPLY_READALL_SUCCESS(HttpStatus.OK, "All replies read successfully");

    private final HttpStatus httpStatus;
    private final String message;
}
