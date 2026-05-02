package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoAddress;
import com.semihkurucay.dto.DtoAddressIU;
import com.semihkurucay.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntityAddress(DtoAddressIU dtoAddressIU);
    Address toUpdateEntityAddress(DtoAddressIU dtoAddressIU, @MappingTarget Address address);
    DtoAddress toDtoAddress(Address address);
}
