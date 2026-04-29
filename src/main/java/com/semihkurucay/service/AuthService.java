package com.semihkurucay.service;

import com.semihkurucay.dto.*;

public interface AuthService {

    DtoAuthLoginResponse login(DtoAuthRequest request);
    DtoAuthResponse refreshToken(DtoAuthRefreshTokenRequest request);
    DtoAuthLoginResponse register(DtoRegisterUser request);
}
