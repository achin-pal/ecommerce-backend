package com.javatechie.service;

import com.javatechie.entity.UserInfo;
import com.javatechie.exception.OrderException;
import com.javatechie.model.Address;
import com.javatechie.model.Order;

import java.util.List;


public interface OrderService {

    public Order createOrder(UserInfo userInfo , Address address);

    public Order findOrderById(Long orderId) throws OrderException;
    public List<Order> userOrderHistory(Long orderId);
    public Order placedOrder(Long orderId) throws OrderException;;
    public Order confirmedOrder(Long orderId) throws OrderException;;
    public Order shippedOrder(Long orderId) throws OrderException;;
    public Order deliveredOrder(Long orderId) throws OrderException;;
    public Order cancelOrder(Long orderId)throws OrderException;
    public List<Order> getAllOrders()throws OrderException;
    public Order deleteOrder(Long orderId)throws OrderException;

}
