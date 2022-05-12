package com.daily.themoti.community.exception;

public class NoSuchReplyExist extends RuntimeException{

    private static final String MESSAGE = "해당 댓글은 존재하지 않습니다.";

    public NoSuchReplyExist() {
        super(MESSAGE);
    }
}