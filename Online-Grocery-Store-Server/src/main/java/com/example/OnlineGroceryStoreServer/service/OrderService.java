package com.example.OnlineGroceryStoreServer.service;

import com.example.OnlineGroceryStoreServer.models.Orders;
import com.example.OnlineGroceryStoreServer.repo.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService
{
    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public Orders orderById(long order_id) throws Exception {
        return ordersRepository.findById(order_id).orElseThrow(()->new Exception("Order is not found for id "+order_id));
    }

    public List<Orders> orderByUserId(long user_id) throws Exception {
        return ordersRepository.findByUserId(user_id);
    }

    public Orders saveOrder(Orders orders){
        return ordersRepository.save(orders);
    }
}
