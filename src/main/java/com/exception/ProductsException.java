package com.exception;

public class ProductsException extends Exception{

    public ProductsException() {

    }
    public ProductsException(Long code) {
        super(String.valueOf(code));
    }


}
