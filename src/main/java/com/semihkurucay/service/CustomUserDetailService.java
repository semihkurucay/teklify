package com.semihkurucay.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailService {

    UserDetails loadUserByUsername(String username);
}
