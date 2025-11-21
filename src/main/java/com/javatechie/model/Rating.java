package com.javatechie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javatechie.entity.UserInfo;
import com.javatechie.entity.UserInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity

public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    private UserInfo user;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Product product;

    @Column(name = "rating")
    private double rating;


    private LocalDateTime createdAt;

}