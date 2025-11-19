package com.javatechie.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private String price;

  @Column(name = "discounted_price")
  private String discounted_price;

  private String quantity;

  private String brand;

  private String color;

  @Column(name = "image_url")
  private String imageUrl;

  @Embedded
  @ElementCollection
  @Column(name = "sizes")
  private Set<Sizes> sizes = new HashSet<>();


  @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Review> reviewList = new ArrayList<>();

  @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Rating> ratingList = new ArrayList<>();

  @Column(name = "num_Rating")
  private String numRating;

  @ManyToOne
  private Category category;

  private LocalDateTime createdAt;

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDiscounted_price() {
    return discounted_price;
  }

  public void setDiscounted_price(String discounted_price) {
    this.discounted_price = discounted_price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Set<Sizes> getSizes() {
    return sizes;
  }

  public void setSizes(Set<Sizes> sizes) {
    this.sizes = sizes;
  }

  public List<Review> getReviewList() {
    return reviewList;
  }

  public void setReviewList(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  public List<Rating> getRatingList() {
    return ratingList;
  }

  public void setRatingList(List<Rating> ratingList) {
    this.ratingList = ratingList;
  }

  public String getNumRating() {
    return numRating;
  }

  public void setNumRating(String numRating) {
    this.numRating = numRating;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Product(Long id, String title, String description, String price, String discounted_price, String quantity, String brand, String color, String imageUrl, Set<Sizes> sizes, List<Review> reviewList, List<Rating> ratingList, String numRating, Category category, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.price = price;
    this.discounted_price = discounted_price;
    this.quantity = quantity;
    this.brand = brand;
    this.color = color;
    this.imageUrl = imageUrl;
    this.sizes = sizes;
    this.reviewList = reviewList;
    this.ratingList = ratingList;
    this.numRating = numRating;
    this.category = category;
    this.createdAt = createdAt;
  }
}