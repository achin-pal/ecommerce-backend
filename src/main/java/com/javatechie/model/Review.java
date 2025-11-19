package com.javatechie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javatechie.entity.UserInfo;
import com.javatechie.entity.UserInfo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    private String review;

    private LocalDateTime createdAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review() {
    }

    public Review(Long id, UserInfo user, Product product, String review, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.review = review;
        this.createdAt = createdAt;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}