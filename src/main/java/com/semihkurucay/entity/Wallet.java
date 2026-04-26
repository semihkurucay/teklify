package com.semihkurucay.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
@Getter
public class Wallet extends BaseEntity {

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "blocked")
    private BigDecimal blocked = BigDecimal.ZERO;

    @Version
    @Column(name = "version")
    private Long version;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private void chackBalance(){
        if (this.balance == null || this.balance.compareTo(BigDecimal.ZERO) < 0){
            // exception fırlat - bakiyelere ulaşılamadı
        }
    }

    private void chackBlocked(){
        if (this.blocked == null || this.blocked.compareTo(BigDecimal.ZERO) < 0){
            // exception fırlat - bakiyelere ulaşılamadı
        }
    }

    private void chackAmount(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            // exception fırlat - tutar null veya 0 ya da 0'dan küçük
        }
    }

    public void addBalance(BigDecimal amount) {
        chackBalance();
        chackAmount(amount);

        this.balance = this.balance.add(amount);
    }

    public void withdrawBalance(BigDecimal amount) {
        chackBalance();
        chackAmount(amount);

        if (this.balance.compareTo(amount) < 0) {
            // exception fırlat - bakiye tutardan büyük
        }

        this.balance = this.balance.subtract(amount);
    }

    public void addBlocked(BigDecimal amount) {
        chackBalance();
        chackBlocked();
        chackAmount(amount);

        withdrawBalance(amount);
        this.blocked = this.blocked.add(amount);
    }

    public void changeAuctionAmount(BigDecimal amount){
        chackBalance();
        chackBlocked();
        chackAmount(amount);

        if (this.blocked.compareTo(amount) < 0){
            // exception fırlat - blokeli tutar çekilecek tutardan büyük
        }

        addBalance(amount);
        this.blocked = this.blocked.subtract(amount);
    }

    public void endAuctionAmount(BigDecimal amount){
        chackBalance();
        chackBlocked();
        chackAmount(amount);

        if (this.blocked.compareTo(amount) < 0){
            // exception fırlat - blokeli tutar çekilecek tutardan büyük
        }

        this.blocked = this.blocked.subtract(amount);
    }
}
