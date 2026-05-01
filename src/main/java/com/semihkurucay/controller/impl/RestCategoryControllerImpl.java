package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RestCategoryController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoCategory;
import com.semihkurucay.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestCategoryControllerImpl extends RestBaseController implements RestCategoryController {

    private final CategoryService categoryService;

    @Override
    public RootEntity<List<DtoCategory>> dropDownList() {
        return ok(categoryService.dropDownList());
    }
}
