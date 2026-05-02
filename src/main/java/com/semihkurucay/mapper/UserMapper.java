package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoRegisterUser;
import com.semihkurucay.dto.DtoUser;
import com.semihkurucay.dto.DtoUserUpdate;
import com.semihkurucay.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, AddressMapper.class, ContactMapper.class})
public interface UserMapper {

    User toEntityUser(DtoRegisterUser dtoRegisterUser);
    User toUpdateEntityUser(DtoUserUpdate dtoUserUpdate, @MappingTarget User user);

    @Mapping(target = "username", source = "login.username")
    DtoUser toDtoUser(User user);
}
