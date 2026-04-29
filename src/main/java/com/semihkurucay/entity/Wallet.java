package com.semihkurucay.entity;

import com.semihkurucay.enums.ErrorType;
import com.semihkurucay.exception.BaseException;
import com.semihkurucay.exception.ErrorMessage;
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

    public Wallet(){};

    public Wallet(User user){
        this.user = user;
        this.balance = BigDecimal.ZERO;
        this.blocked = BigDecimal.ZERO;
    }

    private void chackBalance(){
        if (this.balance == null || this.balance.compareTo(BigDecimal.ZERO) < 0){
            throw new BaseException(new ErrorMessage("Bakiye", ErrorType.NO_VALUE));
        }
    }

    private void chackBlocked(){
        if (this.blocked == null || this.blocked.compareTo(BigDecimal.ZERO) < 0){
            throw new BaseException(new ErrorMessage("Blokeli tutar", ErrorType.NO_VALUE));
        }
    }

    private void chackAmount(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BaseException(new ErrorMessage("Tutar", ErrorType.NO_VALUE));
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

        if (this.balance.compareTo(amount) <= 0) {
            throw new BaseException(new ErrorMessage(null, ErrorType.NOT_ENOUGH_BALANCE));
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

    private void auctionAmount(BigDecimal amount){
        chackBalance();
        chackBlocked();
        chackAmount(amount);

        if (this.blocked.compareTo(amount) < 0){
            throw new BaseException(new ErrorMessage(null, ErrorType.NOT_ENOUGH_BLOCKED));
        }

        this.blocked = this.blocked.subtract(amount);
    }

    public void endAuctionAmount(BigDecimal amount){
        auctionAmount(amount);
    }

    public void changeAuctionAmount(BigDecimal amount){
        auctionAmount(amount);
        addBalance(amount);
    }
}
