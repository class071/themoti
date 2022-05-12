package com.daily.themoti.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    LOAD_SUCCESS(HttpStatus.OK, "Data Load Success");

    private final HttpStatus httpStatus;
    private final String message;
}
