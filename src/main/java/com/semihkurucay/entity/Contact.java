package com.semihkurucay.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact")
@Getter
@Setter
public class Contact extends BaseEntity {

    @Column(name = "phone1", nullable = false, length = 15, unique = true)
    private String phone1;

    @Column(name = "phone2", length = 15)
    private String phone2;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
}
