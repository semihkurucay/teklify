package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/auction-item")
public interface RestAuctionItemController {

    @PostMapping("/create")
    RootEntity<DtoAuctionItemView> createAuctionItem(Principal principal, @Valid @RequestBody DtoAuctionItemCreate dtoAuctionItemCreate);

    @PatchMapping("/cancel/{id}")
    RootEntity<String> cancelAuctionItem(Principal principal, @PathVariable(name = "id") Long auctionItemId);
}
