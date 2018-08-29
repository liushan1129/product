package com.exception;

public class SaleException extends Exception{

    public SaleException() {

    }
    public SaleException(Long code) {
        super(String.valueOf(code));
    }


}
