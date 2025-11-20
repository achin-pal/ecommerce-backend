package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.CartItemException;
import com.javatechie.exception.UserException;
import com.javatechie.model.Cart;
import com.javatechie.model.CartItem;
import com.javatechie.model.Product;
import com.javatechie.repository.CartItemRepository;
import com.javatechie.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartRepository cartRepository;


    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);;
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCratItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        UserInfo userInfo = userService.findByUserId(item.getUserId());

        if(userInfo.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        return cartItemRepository.isCartItemExist(cart,product,size,userId);
    }

    @Override
    public void removeCartItem(Integer userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem = findCartItemById(cartItemId);
        UserInfo userInfo = userService.findByUserId(cartItem.getUserId());

        UserInfo reqUser = userService.findByUserId(userId);
        if(userInfo.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }else {
            throw  new UserException("can not remove other user item, please login");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if(optionalCartItem.isPresent()){
            return optionalCartItem.get();
        }else{
            throw new CartItemException("cart item not found with id "+cartItemId);
        }
    }
}
