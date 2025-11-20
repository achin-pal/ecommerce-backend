package com.javatechie.service;

import com.javatechie.exception.CartItemException;
import com.javatechie.exception.UserException;
import com.javatechie.model.Cart;
import com.javatechie.model.CartItem;
import com.javatechie.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCratItem(Long userId, Long id, CartItem cartItem)
            throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product
            ,String size ,Long userId);

    public void removeCartItem(Integer userId, Long cartItemId)
    throws CartItemException,UserException;

    public CartItem findCartItemById(Long  cartItemId)
        throws CartItemException;
}
