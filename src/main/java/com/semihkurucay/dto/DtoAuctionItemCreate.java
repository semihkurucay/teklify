package com.semihkurucay.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DtoAuctionItemCreate {

    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal startingPrice;
    private BigDecimal minimumPrice;
    private Long categoryId;
    private List<DtoAuctionItemImageIU> image;
    private Long createdUserId;
}
