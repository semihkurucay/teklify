package com.semihkurucay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseDateEntity {

    @Column(name = "tc", nullable = false, length = 11, unique = true)
    private String tc;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", nullable = false)
    private Login login;
}
