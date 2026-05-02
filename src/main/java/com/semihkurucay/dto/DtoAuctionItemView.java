package com.semihkurucay.dto;

import com.semihkurucay.enums.AuctionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DtoAuctionItemView extends DtoBaseDateEntity {

    private String title;
    private BigDecimal currentPrice;
    private AuctionStatus status;
    private String categoryName;
    private LocalDateTime startDate;
    private String createdUserFullName;
    private List<DtoAuctionItemImage> image;
}
