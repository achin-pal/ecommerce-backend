package com.javatechie.repository;

import com.javatechie.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    @Query("SELECT r FROM Rating r WHERE r.productId=:productId")
    public List<Rating> getAllProductsRating(@Param("productId") Long aLong);
}
