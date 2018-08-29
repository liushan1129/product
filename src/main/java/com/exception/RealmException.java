package com.exception;

public class RealmException extends Exception{

    public RealmException() {

    }
    public RealmException(Long code) {
        super(String.valueOf(code));
    }


}
