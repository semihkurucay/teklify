package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoAuctionItemImage;
import com.semihkurucay.dto.DtoAuctionItemImageIU;
import com.semihkurucay.entity.AuctionItemImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuctionItemImageMapper {

    AuctionItemImage toEntityAuctionItemImage(DtoAuctionItemImageIU dtoAuctionItemImageIU);
    DtoAuctionItemImage toDtoAuctionItemImage(AuctionItemImage auctionItemImage);
}
