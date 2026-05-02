package com.semihkurucay.controller;

import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemPageFilter;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.utils.RestPageableEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/auction-item")
public interface RestAuctionItemController {

    @PostMapping("/create")
    RootEntity<DtoAuctionItemView> createAuctionItem(Principal principal, @Valid @RequestBody DtoAuctionItemCreate dtoAuctionItemCreate);

    @PatchMapping("/cancel/{id}")
    RootEntity<String> cancelAuctionItem(Principal principal, @PathVariable(name = "id") Long auctionItemId);

    @GetMapping("/{id}")
    RootEntity<DtoAuctionItem> getAuctionItemById(@PathVariable(name = "id") Long auctionItemId);

    @GetMapping("/find-all-active")
    RootEntity<RestPageableEntity<DtoAuctionItemView>> findAllActiveAuctionItems(@PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestBody DtoAuctionItemPageFilter filter);

    @GetMapping("/find-my-all")
    RootEntity<RestPageableEntity<DtoAuctionItemView>> findMyAllAuctionItems(Principal principal, @PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestBody DtoAuctionItemPageFilter filter);

    @GetMapping("/find-joined-all")
    RootEntity<RestPageableEntity<DtoAuctionItemView>> findJoinedAllAuctionItems(Principal principal, @PageableDefault(size = 10, page = 0, sort = "startDate", direction = Sort.Direction.DESC) Pageable pageable);
}
