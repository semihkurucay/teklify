package com.semihkurucay.exception;

import com.semihkurucay.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String detail;
    private ErrorType errorType;

    public String prepareMessage() {
        String result = "";

        result += "Hata kodu : " + errorType.getCode() + "\n";
        result += "Hata mesajı : " + errorType.getMessage() + "\n";

        if (detail != null && !detail.isEmpty()) {
            result += "Detay : " + detail;
        }

        return result;
    }
}
