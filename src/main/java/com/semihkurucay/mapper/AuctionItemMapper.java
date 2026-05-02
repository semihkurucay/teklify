package com.semihkurucay.mapper;

import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.entity.AuctionItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AuctionItemImageMapper.class)
public interface AuctionItemMapper {



    @Mapping(target = "createdUserFullName", expression = "java(auctionItem.getCreatedUser() != null ? auctionItem.getCreatedUser().getFirstName() + \" \" + auctionItem.getCreatedUser().getLastName() : null)")
    @Mapping(target = "categoryName", source = "auctionItem.category.name")
    DtoAuctionItemView toDtoAuctionItemView(AuctionItem auctionItem);

    @Mapping(target = "createdUserFullName", expression = "java(auctionItem.getCreatedUser() != null ? auctionItem.getCreatedUser().getFirstName() + \" \" + auctionItem.getCreatedUser().getLastName() : null)")
    @Mapping(target = "bidUserFullName", expression = "java(auctionItem.getBidUser() != null ? auctionItem.getBidUser().getFirstName() + \" \" + auctionItem.getBidUser().getLastName() : null)")
    @Mapping(target = "categoryName", source = "auctionItem.category.name")
    DtoAuctionItem toDtoAuctionItem(AuctionItem auctionItem);
    AuctionItem toEntityAuctionItem(DtoAuctionItemCreate dtoAuctionItemCreate);
}
