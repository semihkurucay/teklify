package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/category")
public interface RestCategoryController {

    @GetMapping("/drop-down-list")
    RootEntity<List<DtoCategory>> dropDownList();
}
