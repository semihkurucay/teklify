package com.semihkurucay.controller.impl;

import com.semihkurucay.controller.RestAuctionItemController;
import com.semihkurucay.controller.RootEntity;
import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemPageFilter;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.service.AuctionItemService;
import com.semihkurucay.utils.RestPageableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Override
    public RootEntity<DtoAuctionItem> getAuctionItemById(Long auctionItemId) {
        return ok(auctionItemService.getAuctionItemById(auctionItemId));
    }

    @Override
    public RootEntity<RestPageableEntity<DtoAuctionItemView>> findAllActiveAuctionItems(Pageable pageable, DtoAuctionItemPageFilter filter) {
        Page<DtoAuctionItemView> page = auctionItemService.findAllActiveAuctionItems(pageable, filter);
        return ok(restPageableEntity(page, page.getContent()));
    }

    @Override
    public RootEntity<RestPageableEntity<DtoAuctionItemView>> findMyAllAuctionItems(Principal principal, Pageable pageable, DtoAuctionItemPageFilter filter) {
        Page<DtoAuctionItemView> page = auctionItemService.findMyAllAuctionItems(principal.getName(), pageable, filter);
        return ok(restPageableEntity(page, page.getContent()));
    }

    @Override
    public RootEntity<RestPageableEntity<DtoAuctionItemView>> findJoinedAllAuctionItems(Principal principal, Pageable pageable) {
        Page<DtoAuctionItemView> page = auctionItemService.findJoinedAllAuctionItems(principal.getName(), pageable);
        return ok(restPageableEntity(page, page.getContent()));
    }
}
