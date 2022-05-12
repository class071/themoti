package com.daily.themoti.community.exception;

public class NoSuchPostExist extends RuntimeException {

    private static final String MESSAGE = "해당 게시글은 존재하지 않습니다.";

    public NoSuchPostExist() {
        super(MESSAGE);
    }
}
