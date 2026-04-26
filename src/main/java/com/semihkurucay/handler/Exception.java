package com.semihkurucay.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exception<T> {

    private String path;
    private T exception;
    private LocalDateTime createDate = LocalDateTime.now();
}
