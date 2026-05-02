package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoContact;
import com.semihkurucay.dto.DtoContactIU;
import com.semihkurucay.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toEntityContact(DtoContactIU dtoContactIU);
    Contact toUpdateEntityContact(DtoContactIU dtoContactIU, @MappingTarget Contact contact);
    DtoContact toDtoContact(Contact contact);
}
