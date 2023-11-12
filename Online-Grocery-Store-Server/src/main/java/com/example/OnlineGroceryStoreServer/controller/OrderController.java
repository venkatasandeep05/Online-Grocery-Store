package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Cart;
import com.example.OnlineGroceryStoreServer.models.Orders;
import com.example.OnlineGroceryStoreServer.payload.CheckoutRequest;
import com.example.OnlineGroceryStoreServer.repo.CartRespository;
import com.example.OnlineGroceryStoreServer.service.CartService;
import com.example.OnlineGroceryStoreServer.service.OrderService;
import com.example.OnlineGroceryStoreServer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/api")
public class OrderController
{
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    CartRespository cartRespository;

    @PostMapping("/checkout_order")
    @Transactional
    public ResponseEntity<?> checkout_order(@RequestBody CheckoutRequest request) {

        try {
            long userId = request.getUserId();
            double price = request.getPrice();
            Optional<List<Cart>> cartProducts = cartRespository.findByUserId(request.getUserId());
            List<Long> productList=new ArrayList<>();
            if(!cartProducts.isEmpty()) {
                for (Cart x : cartProducts.get())
                    productList.add(x.getProductId());
                if (request.getProductIds().equals(productList)) {
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
                        List<Orders> obj = orderService.orderByUserId(userId);
                        return ResponseEntity.ok(obj);
                    }
                    else {
                        throw new Exception("Total amount is mismatch");
                    }
                }
                else {
                    throw new Exception("Cart doesn't have the existing products");
                }
            }
            else
            {
                throw new Exception("Cart is empty");
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
