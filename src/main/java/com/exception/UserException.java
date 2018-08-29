package com.exception;

public class UserException extends Exception{

    public UserException() {

    }
    public UserException(Long code) {
        super(String.valueOf(code));
    }


}
