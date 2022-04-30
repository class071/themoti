package com.daily.themoti.global.advice;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.global.constant.PostErrorCode;
import com.daily.themoti.global.exception.NoSuchPostExist;
import com.daily.themoti.global.exception.NoSuchReplyExist;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchPostExist.class)
    public ApiResponse handleNoSuchPostExist(NoSuchPostExist e) {
        PostErrorCode errorCode = PostErrorCode.POST_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }

    @ExceptionHandler(NoSuchReplyExist.class)
    public ApiResponse handleNoSuchReplyExist(NoSuchReplyExist exist) {
        PostErrorCode errorCode = PostErrorCode.REPLY_NOT_FOUND;
        return ApiResponse.error(errorCode.name(), errorCode.getHttpStatus(), errorCode.getMessage());
    }
}
