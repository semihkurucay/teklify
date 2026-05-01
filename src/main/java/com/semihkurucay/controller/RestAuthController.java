package com.semihkurucay.controller;

import com.semihkurucay.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface RestAuthController {

    @PostMapping("/login")
    RootEntity<DtoAuthLoginResponse> login(@Valid @RequestBody DtoAuthRequest request);

    @PostMapping("/refresh-token")
    RootEntity<DtoAuthResponse> refreshToken(@Valid @RequestBody DtoAuthRefreshTokenRequest request);

    @PostMapping("/register")
    RootEntity<DtoAuthLoginResponse> register(@Valid @RequestBody DtoRegisterUser register);
}
