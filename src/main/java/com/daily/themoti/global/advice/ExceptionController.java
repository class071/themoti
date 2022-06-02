package com.daily.themoti.global.advice;

import com.daily.themoti.global.api.ApiResponse;
import com.daily.themoti.community.exception.NoSuchPostExist;
import com.daily.themoti.community.exception.NoSuchReplyExist;
import com.daily.themoti.user.exception.UnauthorizedUserException;
import com.daily.themoti.user.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.daily.themoti.community.constant.CommunityConstant.POST_NOT_EXIST;
import static com.daily.themoti.community.constant.CommunityConstant.REPLY_NOT_EXIST;
import static com.daily.themoti.smokeamount.constant.SmokeAmountConstant.*;
import static com.daily.themoti.user.constant.UserConstant.UNAUTHORIZED;
import static com.daily.themoti.user.constant.UserConstant.USER_NOT_EXIST;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoSuchPostExist.class)
    public ApiResponse handleNoSuchPostExist(NoSuchPostExist e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.NOT_FOUND, POST_NOT_EXIST);
    }

    @ExceptionHandler(NoSuchReplyExist.class)
    public ApiResponse handleNoSuchReplyExist(NoSuchReplyExist e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.NOT_FOUND, REPLY_NOT_EXIST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.BAD_REQUEST, NOT_CORRECT_VALUE);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.BAD_REQUEST, NOT_ENOUGH_PARAM);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.BAD_REQUEST, NOT_CORRECT_PARAM_TYPE);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ApiResponse handleUnauthorizedUserException(UnauthorizedUserException e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.UNAUTHORIZED, UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ApiResponse handleUserNotExistException(UserNotExistException e) {
        log.error(e.getMessage());
        return ApiResponse.error(HttpStatus.NOT_FOUND, USER_NOT_EXIST);
    }
}
