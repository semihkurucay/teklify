package com.semihkurucay.dto;

import com.semihkurucay.enums.AuctionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DtoAuctionItem extends DtoBaseDateEntity {

    private String title;
    private String description;
    private Boolean isActive;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal startingPrice;
    private BigDecimal minimumPrice;
    private BigDecimal currentPrice;
    private AuctionStatus status;
    private String categoryName;
    private List<DtoAuctionItemImage> image;
    private String createdUserFullName;
    private String bidUserFullName;
}
