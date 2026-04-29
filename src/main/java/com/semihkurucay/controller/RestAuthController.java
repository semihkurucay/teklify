package com.semihkurucay.controller;

import com.semihkurucay.dto.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
public interface RestAuthController {

    @PostMapping("/login")
    RootEntity<DtoAuthLoginResponse> login(DtoAuthRequest request);

    @PostMapping("/refresh-token")
    RootEntity<DtoAuthResponse> refreshToken(DtoAuthRefreshTokenRequest request);

    @PostMapping("/register")
    RootEntity<DtoAuthLoginResponse> register(DtoRegisterUser register);
}
