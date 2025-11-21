package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.Product;
import com.javatechie.model.Rating;
import com.javatechie.model.RatingRequest;
import com.javatechie.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    private RatingRepository ratingRepository;
    private AllProductService allProductService;

    @Override
    public Rating createRating(RatingRequest ratingRequest, UserInfo userInfo) throws ProductException {
        Product product = allProductService.findProductById(ratingRequest.getProductId());


        Rating rating= new Rating();
        rating.setProduct(product);
        rating.setUser(userInfo);
        rating.setRating(ratingRequest.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) throws ProductException {
        return ratingRepository.getAllProductsRating(productId);
    }
}
