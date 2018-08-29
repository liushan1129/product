package com.exception;

public class MaterialException extends Exception{

    public MaterialException() {

    }
    public MaterialException(Long code) {
        super(String.valueOf(code));
    }


}
