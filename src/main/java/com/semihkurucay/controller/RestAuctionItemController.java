package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/auction-item")
public interface RestAuctionItemController {

    @PostMapping("/create")
    RootEntity<DtoAuctionItemView> createAuctionItem(Principal principal, DtoAuctionItemCreate dtoAuctionItemCreate);

    @PatchMapping("/cancel")
    RootEntity<String> cancelAuctionItem(Principal principal, Long auctionItemId);
}
