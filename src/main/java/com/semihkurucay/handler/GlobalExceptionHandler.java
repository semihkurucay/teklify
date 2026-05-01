package com.semihkurucay.handler;

import com.semihkurucay.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private <T> ApiError<T> createApiError(HttpStatus status, WebRequest request, T errorMessage) {
        Exception<T> exception = new Exception<>();
        exception.setException(errorMessage);
        exception.setPath(request.getDescription(false).substring(5));

        ApiError<T> apiError = new ApiError<>();
        apiError.setStatus(status.value());
        apiError.setException(exception);

        return apiError;
    }

    private List<String> listToAddValue(List<String> list, String value) {
        list.add(value);
        return list;
    }

    private Map<String, List<String>> getErrorMessage(BindingResult bindingResult) {
        Map<String, List<String>> map = new HashMap<>();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            String fieldName = ((FieldError) objectError).getField();

            if (map.containsKey(fieldName)) {
                map.put(fieldName, listToAddValue(map.get(fieldName), objectError.getDefaultMessage()));
            } else {
                map.put(fieldName, listToAddValue(new ArrayList<String>(), objectError.getDefaultMessage()));
            }
        }

        return map;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(
                        createApiError(
                                HttpStatus.BAD_REQUEST,
                                request,
                                getErrorMessage(ex.getBindingResult())
                        )
                );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(
                        createApiError(
                                ex.getErrorMessage().getErrorType().getHttpStatus(),
                                request,
                                ex.getMessage()
                        )
                );
    }
}
