package com.javatechie.service;


import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.Product;
import com.javatechie.model.Review;
import com.javatechie.model.ReviewRequest;
import com.javatechie.repository.ProductRepository;
import com.javatechie.repository.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AllProductService allProductService;
    @Autowired
    private ProductRepository productRepository;



    @Override
    public Review createReview(ReviewRequest req, UserInfo user) throws ProductException {
        Product product =allProductService.findProductById(req.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
