package com.daily.themoti.smokearea.exception;

public class WrongURLException extends RuntimeException{

    private static final String MESSAGE = "잘못된 key 혹은 URL 요청입니다.";

    public WrongURLException(){
        super(MESSAGE);
    }
}
