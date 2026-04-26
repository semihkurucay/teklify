package com.semihkurucay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
public class RefreshToken extends BaseDateEntity {

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "expTime", nullable = false)
    private LocalDateTime expTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
