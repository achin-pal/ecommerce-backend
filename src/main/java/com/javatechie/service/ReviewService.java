package com.javatechie.service;

import ch.qos.logback.core.model.processor.ProcessorException;
import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.Review;
import com.javatechie.model.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, UserInfo user) throws ProductException;

    public List<Review> getAllReviews(Long productId);
}
