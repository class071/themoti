package com.daily.themoti.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PostSuccessCode {

    POST_CREATE_SUCCESS(HttpStatus.OK, "Post created successfully"),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "Post updated successfully"),
    POST_DELETE_SUCCESS(HttpStatus.OK, "Post deleted successfully"),
    POST_READONE_SUCCESS(HttpStatus.OK, "A post read successfully"),
    POST_READALL_SUCCESS(HttpStatus.OK, "All posts read successfully");

    private final HttpStatus httpStatus;
    private final String message;
}
