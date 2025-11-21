package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.ProductException;
import com.javatechie.model.AddItemRequest;
import com.javatechie.model.Cart;
import com.javatechie.model.CartItem;
import com.javatechie.model.Product;
import com.javatechie.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private AllProductService allProductService;

    @Override
    public Cart createCart(UserInfo userInfo) {
        Cart cart = new Cart();
        cart.setUserInfo(userInfo);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest addItemRequest) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = allProductService.findProductById(addItemRequest.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart,product,addItemRequest.getSize(),userId);

        if(isPresent == null ){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(addItemRequest.getQuantity());
            cartItem.setUserId(userId);

            int price = addItemRequest.getQuantity()*product.getDiscountedPrice();

            cartItem.setPrice(price);
            cartItem.setSize(addItemRequest.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItemSet().add(createdCartItem);

        }
        return "Item added to cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice =0;
        int discountedPrice =0;
        int totalItem=0;

        for (CartItem cartItem : cart.getCartItemSet()) {

            totalPrice=totalPrice+cartItem.getPrice();
            discountedPrice = discountedPrice+cartItem.getDiscountedPrice();
            totalItem = totalItem+cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(discountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-discountedPrice);
        cart.setTotalItem(totalItem);

        return cartRepository.save(cart);
    }
}
