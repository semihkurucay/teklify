package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.DtoUserUpdate;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
public interface RestUserController {

    @GetMapping("/")
    RootEntity<DtoUser> getMyProfile(Principal principal);

    @PutMapping("/")
    RootEntity<DtoUser> updateMyProfile(Principal principal, @Valid @RequestBody DtoUserUpdate dtoUserUpdate);
}
