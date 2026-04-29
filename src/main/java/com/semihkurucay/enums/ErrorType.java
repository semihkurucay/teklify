package com.semihkurucay.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    GENERAL_ERROR("0x001", "General Error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_VALUE("0x002", "Değere ulaşılmadı, bulunamadı!", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BALANCE("0x003", "Yetersiz bakiye!", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BLOCKED("0x004", "Yetersiz blokeli tutar!", HttpStatus.BAD_REQUEST)
    ;

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorType(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
