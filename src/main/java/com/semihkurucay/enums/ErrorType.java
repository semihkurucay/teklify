package com.semihkurucay.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    GENERAL_ERROR("0x001", "General Error", HttpStatus.INTERNAL_SERVER_ERROR),
    NO_VALUE("0x002", "Değere ulaşılmadı, bulunamadı!", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BALANCE("0x003", "Yetersiz bakiye!", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BLOCKED("0x004", "Yetersiz blokeli tutar!", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("0x005", "Token Süresi dolmuştur!", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED("0x006", "Refresh token süresi dolmuştur.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("0x006", "Kullanıcı bulunamadı!", HttpStatus.UNAUTHORIZED),
    INCORRECT_CREDENTIALS("0x007", "Yanlış kullanıcı adı veya şifre!", HttpStatus.UNAUTHORIZED),
    REPETITIVE_RECORDING("0x009", "Bu kayıt zaten mevcut.", HttpStatus.CONFLICT),
    CANCELATION_NOT_ALLOWED_EXPIRED("0x010", "Son 15 dakika kala ihale iptal edilemez!", HttpStatus.BAD_REQUEST),
    AUCTION_NOT_ACTIVE("0x011", "İhale aktif değil!", HttpStatus.BAD_REQUEST),
    START_DATE_MUST_BE_AFTER_CURRENT_DATE("0x012", "Başlangıç tarihi mevcut tarihden sonra olmalıdır!", HttpStatus.BAD_REQUEST),
    END_DATE_MUST_BE_AFTER_START_DATE("0x013", "Bitiş tarihi başlangıç tarihinden en az 1 saat sonra olmalı!", HttpStatus.BAD_REQUEST),
    USER_NOT_ELIGIBLE("0x014", "Kullanıcı 18 yaşından küçük!", HttpStatus.BAD_REQUEST),
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
