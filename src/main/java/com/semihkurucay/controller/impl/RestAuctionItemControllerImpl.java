package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RestAuctionItemController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.service.AuctionItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
class RestAuctionItemControllerImpl extends RestBaseController implements RestAuctionItemController {

    private final AuctionItemService auctionItemService;

    @Override
    public RootEntity<DtoAuctionItemView> createAuctionItem(Principal principal, DtoAuctionItemCreate dtoAuctionItemCreate) {
        return ok(auctionItemService.createAuctionItem(principal.getName(), dtoAuctionItemCreate));
    }

    @Override
    public RootEntity<String> cancelAuctionItem(Principal principal, @PathVariable(name = "id") Long auctionItemId) {
        auctionItemService.cancelAuctionItem(principal.getName(), auctionItemId);
        return ok("Complated");
    }
}
