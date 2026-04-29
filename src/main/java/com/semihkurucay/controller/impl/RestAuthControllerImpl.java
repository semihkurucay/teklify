package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RestAuthController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.*;
import com.semihkurucay.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestAuthControllerImpl extends RestBaseController implements RestAuthController {

    private final AuthService authService;

    @Override
    public RootEntity<DtoAuthLoginResponse> login(@Valid @RequestBody DtoAuthRequest request) {
        return ok(authService.login(request));
    }

    @Override
    public RootEntity<DtoAuthResponse> refreshToken(@Valid @RequestBody DtoAuthRefreshTokenRequest request) {
        return ok(authService.refreshToken(request));
    }

    @Override
    public RootEntity<DtoAuthLoginResponse> register(@Valid @RequestBody DtoRegisterUser register) {
        return ok(authService.register(register));
    }
}
