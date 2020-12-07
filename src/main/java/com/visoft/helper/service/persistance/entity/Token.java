package com.visoft.helper.service.persistance.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;

@Entity
@Getter
@Setter
public class Token extends IdEntity {

    @Column(nullable = false)
    private Instant expiration;

    @Column(nullable = false, unique = true)
    private String token;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}