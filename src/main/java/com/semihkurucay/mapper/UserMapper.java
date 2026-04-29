package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoRegisterUser;
import com.semihkurucay.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, AddressMapper.class, ContactMapper.class})
public interface UserMapper {

    User toEntityUser(DtoRegisterUser dtoRegisterUser);
}
