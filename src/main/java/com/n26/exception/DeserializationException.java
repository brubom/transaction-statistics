package com.n26.exception;

public class DeserializationException extends RuntimeException {



    public enum ErrorCodes{
        INVALID_JSON,
        INVALID_FIELD_VALUE;

    }

    private ErrorCodes errorCode;

    public DeserializationException(ErrorCodes errorCode) {

        this.errorCode = errorCode;
    }

    public ErrorCodes getErrorCode(){
        return this.errorCode;
    }
}
