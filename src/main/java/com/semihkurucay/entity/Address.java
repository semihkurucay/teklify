package com.semihkurucay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address extends BaseEntity {

    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @Column(name = "district", nullable = false, length = 30)
    private String district;

    @Column(name = "description", nullable = false, length = 255)
    private String description;
}
