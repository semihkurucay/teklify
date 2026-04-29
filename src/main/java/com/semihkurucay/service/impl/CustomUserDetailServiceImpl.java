package com.semihkurucay.service.impl;

import com.semihkurucay.entity.CustomUserDetail;
import com.semihkurucay.entity.Login;
import com.semihkurucay.enums.ErrorType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.repository.LoginRepository;
import com.semihkurucay.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class CustomUserDetailServiceImpl implements CustomUserDetailService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Login login = loginRepository.findByUsername((username))
                .orElseThrow(() -> new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_FOUND)));

        return new CustomUserDetail(login.getUsername(), login.getPassword());
    }
}
