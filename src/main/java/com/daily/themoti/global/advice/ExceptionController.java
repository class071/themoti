package com.daily.themoti.global.advice;

import com.daily.themoti.global.api.ApiResponse;

import com.daily.themoti.community.exception.NoSuchPostExist;
import com.daily.themoti.community.exception.NoSuchReplyExist;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.daily.themoti.community.constant.CommunityConstant.POST_NOT_EXIST;
import static com.daily.themoti.community.constant.CommunityConstant.REPLY_NOT_EXIST;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchPostExist.class)
    public ApiResponse handleNoSuchPostExist(NoSuchPostExist e) {
        return ApiResponse.error(HttpStatus.NOT_FOUND, POST_NOT_EXIST);
    }

    @ExceptionHandler(NoSuchReplyExist.class)
    public ApiResponse handleNoSuchReplyExist(NoSuchReplyExist exist) {
        return ApiResponse.error(HttpStatus.NOT_FOUND, REPLY_NOT_EXIST);
    }
}
