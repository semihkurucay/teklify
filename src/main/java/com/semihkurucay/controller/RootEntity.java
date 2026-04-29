package com.semihkurucay.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RootEntity <T> {

    private Integer status;
    private T data;

    public static <T> RootEntity<T> ok(T data){
        RootEntity<T> rootEntity = new RootEntity<>();
        rootEntity.setStatus(200);
        rootEntity.setData(data);

        return rootEntity;
    }
}
