package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Orders;
import com.example.OnlineGroceryStoreServer.payload.CheckoutRequest;
import com.example.OnlineGroceryStoreServer.service.CartService;
import com.example.OnlineGroceryStoreServer.service.OrderService;
import com.example.OnlineGroceryStoreServer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class OrderController
{
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @PostMapping("/checkout_order")
    public ResponseEntity<?> checkout_order(@RequestBody CheckoutRequest request) {

        try {
            long userId = request.getUserId();
            double price = request.getPrice();
            if (productService.checkTotalAmountAgainstCart(price, request.getProductIds())) {
                Orders order = new Orders();
                order.setId(getOrderId());
                order.setUserId(userId);
                order.setPrice(price);
                order.setPaymentType(request.getPaymentType());
                order.setDeliveryAddress(request.getDeliveryAddress());
                order.setProductIds(request.getProductIds());
                order.setEmailId(request.getEmailId());
                order.setCreatedAt(new Date());
                orderService.saveOrder(order);
                cartService.removeAllCartByUserId(userId);
                List<Orders> obj= orderService.orderByUserId(userId);
                return ResponseEntity.ok(obj);
            } else {
                throw new Exception("Total amount is mismatch");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public int getOrderId() {
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    @GetMapping("/getOrdersByUserId/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        try {
            List<Orders> orders= orderService.orderByUserId(userId);
            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
