package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.entity.AuctionItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AuctionItemImageMapper.class)
public interface AuctionItemMapper {

    AuctionItem toEntityAuctionItem(DtoAuctionItemCreate dtoAuctionItemCreate);
    DtoAuctionItemView toDtoAuctionItemView(AuctionItem auctionItem);
    DtoAuctionItem toDtoAuctionItem(AuctionItem auctionItem);
}
