package com.semihkurucay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bid")
@Getter
@Setter
public class Bid extends BaseDateEntity {

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "auction_item_id")
    private AuctionItem auctionItem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
