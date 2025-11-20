package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.AddItemRequest;
import com.javatechie.model.Cart;

public interface CartService {

    public Cart createCart(UserInfo userInfo);
    public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException;

    public Cart findUserCart(Long userId);
}
