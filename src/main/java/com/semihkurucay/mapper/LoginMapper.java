package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoRegisterLogin;
import com.semihkurucay.entity.Login;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    Login toEntityLogin(DtoRegisterLogin dtoRegisterLogin);
}
