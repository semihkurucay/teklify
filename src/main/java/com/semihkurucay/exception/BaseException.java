package com.semihkurucay.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.prepareMessage());
        this.errorMessage = errorMessage;
    }
}
