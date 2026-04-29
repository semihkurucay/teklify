package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RootEntity;

class RestBaseController {

    public <T>RootEntity<T> ok(T data) {
        return RootEntity.ok(data);
    }
}
