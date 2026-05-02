package com.semihkurucay.service;

import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemPageFilter;
import com.semihkurucay.dto.DtoAuctionItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuctionItemService {

    DtoAuctionItemView createAuctionItem(String username, DtoAuctionItemCreate dtoAuctionItemCreate);
    void cancelAuctionItem(String username, Long auctionItemId);
    DtoAuctionItem getAuctionItemById(Long auctionItemId);
    Page<DtoAuctionItemView>  findAllActiveAuctionItems(Pageable pageable, DtoAuctionItemPageFilter filter);
    Page<DtoAuctionItemView> findMyAllAuctionItems(String username, Pageable pageable, DtoAuctionItemPageFilter filter);
    Page<DtoAuctionItemView> findJoinedAllAuctionItems(String username, Pageable pageable);
}
