package com.exception;

public class TypeException extends Exception{

    public TypeException() {

    }
    public TypeException(Long code) {
        super(String.valueOf(code));
    }


}
