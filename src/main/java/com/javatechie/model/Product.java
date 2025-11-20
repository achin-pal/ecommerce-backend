package com.javatechie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
  private Integer price;

  @Column(name = "discountedPrice")
  private int discountedPrice;

  @Column(name = "discountPresent")
  private int discountPresent;

  public int getDiscountedPresent() {
    return discountPresent;
  }

  public void setDiscountedPresent(int discountPresent) {
    this.discountPresent = discountPresent;
  }

  private Integer quantity;

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


}