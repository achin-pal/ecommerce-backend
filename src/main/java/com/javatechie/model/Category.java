package com.javatechie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(max = 50)
  private String name;

  private int level;

  public Category getCategoryPrent() {
    return categoryPrent;
  }

  public void setCategoryPrent(Category categoryPrent) {
    this.categoryPrent = categoryPrent;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public @NotNull @Size(max = 50) String getName() {
    return name;
  }

  public void setName(@NotNull @Size(max = 50) String name) {
    this.name = name;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_cat_id")
  private Category categoryPrent;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Category() {
  }

  public Category(Long id, String name, int level, Category categoryPrent) {
    this.id = id;
    this.name = name;
    this.level = level;
    this.categoryPrent = categoryPrent;
  }
}