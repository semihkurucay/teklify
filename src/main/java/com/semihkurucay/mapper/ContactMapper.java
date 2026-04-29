package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoContactIU;
import com.semihkurucay.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toEntityContact(DtoContactIU dtoContactIU);
}
