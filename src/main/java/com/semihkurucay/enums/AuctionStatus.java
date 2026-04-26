package com.semihkurucay.enums;

import lombok.Getter;

@Getter
public enum AuctionStatus {

    CREATED("Oluşturuldu"),
    FINISHED("Bitti"),
    STARTED("Başladı"),
    CANCELLED("İptal Edildi");

    private final String description;

    AuctionStatus(String description) {
        this.description = description;
    }
}
