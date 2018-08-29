package com.exception;

public class PartnerException extends Exception{

    public PartnerException() {

    }
    public PartnerException(Long code) {
        super(String.valueOf(code));
    }


}
