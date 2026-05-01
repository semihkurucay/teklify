package com.semihkurucay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auction_item_image")
@Getter
@Setter
public class AuctionItemImage extends BaseEntity {

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "auction_item_id")
    private AuctionItem auctionItem;
}
