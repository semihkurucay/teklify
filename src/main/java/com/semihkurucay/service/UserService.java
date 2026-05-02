package com.semihkurucay.service;

import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.DtoUserUpdate;

public interface UserService {

    DtoUser getUser(String username);
    DtoUser updateUser(String username, DtoUserUpdate dtoUserUpdate);
}
