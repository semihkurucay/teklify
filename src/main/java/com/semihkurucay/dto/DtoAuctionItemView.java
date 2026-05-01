package com.semihkurucay.dto;

import com.semihkurucay.enums.AuctionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoAuctionItemView extends DtoBaseDateEntity {

    private String title;
    private BigDecimal currentPrice;
    private AuctionStatus status;
    private String categoryName;
    private LocalDateTime startDate;
    private List<DtoAuctionItemImage> image;
}
