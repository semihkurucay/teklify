package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntityAddress(DtoAddressIU dtoAddressIU);
}
