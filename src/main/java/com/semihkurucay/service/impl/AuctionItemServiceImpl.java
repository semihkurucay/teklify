package com.semihkurucay.service.impl;

import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemView;
import com.semihkurucay.entity.AuctionItem;
import com.semihkurucay.entity.Category;
import com.semihkurucay.entity.User;
import com.semihkurucay.enums.AuctionStatus;
import com.semihkurucay.enums.ErrorType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
import com.semihkurucay.mapper.AuctionItemMapper;
import com.semihkurucay.repository.AuctionItemRepository;
import com.semihkurucay.repository.CategoryRepository;
import com.semihkurucay.repository.UserRepository;
import com.semihkurucay.service.AuctionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuctionItemServiceImpl implements AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AuctionItemMapper auctionItemMapper;

    private Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Kategori", ErrorType.NO_VALUE)));
    }

    private User getUserByUsername(String username) {
        return userRepository.findByLogin_username(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(null, ErrorType.USER_NOT_FOUND)));
    }

    @Transactional
    @Override
    public DtoAuctionItemView createAuctionItem(String username, DtoAuctionItemCreate dtoAuctionItemCreate) {
        if(dtoAuctionItemCreate.getStartDate().isBefore(LocalDateTime.now())){
            throw new BaseException(new ErrorMessage(null, ErrorType.START_DATE_MUST_BE_AFTER_CURRENT_DATE));
        }

        if(dtoAuctionItemCreate.getStartDate().plusHours(1).isAfter(dtoAuctionItemCreate.getEndDate())){
            throw new BaseException(new ErrorMessage(null, ErrorType.END_DATE_MUST_BE_AFTER_START_DATE));
        }

        AuctionItem auctionItem = auctionItemMapper.toEntityAuctionItem(dtoAuctionItemCreate);
        auctionItem.setCreatedUser(getUserByUsername(username));
        auctionItem.setIsActive(true);
        auctionItem.setCurrentPrice(auctionItem.getStartingPrice());
        auctionItem.setStatus(AuctionStatus.CREATED);
        auctionItem.setCategory(getCategoryById(dtoAuctionItemCreate.getCategoryId()));

        auctionItem.getImage()
                .forEach(image -> image.setAuctionItem(auctionItem));

        DtoAuctionItemView dto = auctionItemMapper.toDtoAuctionItemView(auctionItemRepository.save(auctionItem));
        dto.setCategoryName(auctionItem.getCategory().getName());
        return dto;
    }

    @Transactional
    @Override
    public void cancelAuctionItem(String username, Long auctionItemId) {
        AuctionItem auctionItem = auctionItemRepository.findMyAuctionItemById(username, auctionItemId)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Teklif", ErrorType.NO_VALUE)));

        if(auctionItem.getStartDate().plusMinutes(-15).isBefore(LocalDateTime.now()) || auctionItem.getStatus() != AuctionStatus.CREATED){
            throw new BaseException(new ErrorMessage(null, ErrorType.CANCELATION_NOT_ALLOWED_EXPIRED));
        }

        if(auctionItem.getIsActive() == false){
            throw new BaseException(new ErrorMessage(null, ErrorType.AUCTION_NOT_ACTIVE));
        }

        auctionItem.setIsActive(false);
        auctionItem.setStatus(AuctionStatus.CANCELLED);
        auctionItemRepository.save(auctionItem);
    }
}
