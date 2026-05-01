package com.semihkurucay.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestPageableEntity <T> {

    private List<T> content;
    private Integer number;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
}
