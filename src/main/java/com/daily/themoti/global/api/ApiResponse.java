package com.daily.themoti.global.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String code;
    private HttpStatus httpStatus;
    private String message;
    private T response;

    public static <T> ApiResponse success(String code, HttpStatus httpStatus, String message, T response) {
        return new ApiResponse<>(code, httpStatus, message, response);
    }

    public static ApiResponse error(String code, HttpStatus httpStatus, String message) {
        return new ApiResponse<>(code, httpStatus, message, null);
    }
}
