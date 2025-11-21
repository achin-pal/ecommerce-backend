package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.Rating;
import com.javatechie.model.RatingRequest;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    public Rating createRating(RatingRequest ratingRequest, UserInfo userInfo) throws ProductException;

    public List<Rating> getProductsRating(Long userId) throws ProductException;


}
