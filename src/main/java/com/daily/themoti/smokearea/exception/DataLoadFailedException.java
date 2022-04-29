package com.daily.themoti.smokearea.exception;

public class DataLoadFailedException extends RuntimeException{

    private static final String MESSAGE = "데이터 로드에 실패하였습니다.";

    public DataLoadFailedException(){
        super(MESSAGE);
    }
}
