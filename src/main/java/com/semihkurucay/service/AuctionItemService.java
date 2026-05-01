package com.semihkurucay.service;

import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;

public interface AuctionItemService {

    DtoAuctionItemView createAuctionItem(String username, DtoAuctionItemCreate dtoAuctionItemCreate);
    void cancelAuctionItem(String username, Long auctionItemId);
}
