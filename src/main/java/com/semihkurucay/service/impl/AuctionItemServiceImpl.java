package com.semihkurucay.service.impl;

import com.semihkurucay.dto.DtoAuctionItem;
import com.semihkurucay.dto.DtoAuctionItemCreate;
import com.semihkurucay.dto.DtoAuctionItemPageFilter;
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
import com.semihkurucay.repository.BidRepository;
import com.semihkurucay.repository.CategoryRepository;
import com.semihkurucay.repository.UserRepository;
import com.semihkurucay.service.AuctionItemService;
import com.semihkurucay.specification.AuctionItemSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class AuctionItemServiceImpl implements AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;
    private final BidRepository bidRepository;
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

    private Boolean chackDateControl(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate.isBefore(endDate);
    }

    @Transactional
    @Override
    public DtoAuctionItemView createAuctionItem(String username, DtoAuctionItemCreate dtoAuctionItemCreate) {
        if(chackDateControl(dtoAuctionItemCreate.getStartDate(), LocalDateTime.now())){
            throw new BaseException(new ErrorMessage(null, ErrorType.START_DATE_MUST_BE_AFTER_CURRENT_DATE));
        }

        if(chackDateControl(dtoAuctionItemCreate.getEndDate(), dtoAuctionItemCreate.getStartDate().plusHours(1))){
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

        return auctionItemMapper.toDtoAuctionItemView(auctionItemRepository.save(auctionItem));
    }

    @Transactional
    @Override
    public void cancelAuctionItem(String username, Long auctionItemId) {
        AuctionItem auctionItem = auctionItemRepository.findMyAuctionItemById(username, auctionItemId)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Teklif", ErrorType.NO_VALUE)));

        if(chackDateControl(auctionItem.getStartDate().plusMinutes(-15), LocalDateTime.now()) || auctionItem.getStatus() != AuctionStatus.CREATED){
            throw new BaseException(new ErrorMessage(null, ErrorType.CANCELATION_NOT_ALLOWED_EXPIRED));
        }

        if(auctionItem.getIsActive() == false){
            throw new BaseException(new ErrorMessage(null, ErrorType.AUCTION_NOT_ACTIVE));
        }

        auctionItem.setIsActive(false);
        auctionItem.setStatus(AuctionStatus.CANCELLED);
        auctionItemRepository.save(auctionItem);
    }

    @Override
    public DtoAuctionItem getAuctionItemById(Long auctionItemId) {
        AuctionItem auctionItem = auctionItemRepository.findById(auctionItemId)
                .orElseThrow(() -> new BaseException(new ErrorMessage("Teklif", ErrorType.NO_VALUE)));

        return auctionItemMapper.toDtoAuctionItem(auctionItem);
    }

    @Override
    public Page<DtoAuctionItemView> findAllActiveAuctionItems(Pageable pageable, DtoAuctionItemPageFilter filter) {
        Specification<AuctionItem> spec = AuctionItemSpecification.pageFilterAuctionItem(filter.getCategoryId(), filter.getSearch(), true, null);
        return auctionItemRepository.findAll(spec, pageable)
                .map(auctionItemMapper::toDtoAuctionItemView);
    }

    @Override
    public Page<DtoAuctionItemView> findMyAllAuctionItems(String username, Pageable pageable, DtoAuctionItemPageFilter filter) {
        Specification<AuctionItem> spec = AuctionItemSpecification.pageFilterAuctionItem(filter.getCategoryId(), filter.getSearch(), null, getUserByUsername(username).getId());
        return auctionItemRepository.findAll(spec, pageable)
                .map(auctionItemMapper::toDtoAuctionItemView);
    }

    @Override
    public Page<DtoAuctionItemView> findJoinedAllAuctionItems(String username, Pageable pageable) {
        User user = getUserByUsername(username);
        return bidRepository.findJoinedAllAuctionItems(user.getId(), pageable)
                .map(auctionItemMapper::toDtoAuctionItemView);
    }
}
