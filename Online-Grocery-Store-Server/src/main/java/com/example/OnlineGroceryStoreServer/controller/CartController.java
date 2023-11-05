package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Cart;
import com.example.OnlineGroceryStoreServer.payload.AddToCart;
import com.example.OnlineGroceryStoreServer.payload.RemoveFromCart;
import com.example.OnlineGroceryStoreServer.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController
{
      @Autowired
      CartService cartService;

      @PostMapping ("/addProduct")
      public ResponseEntity<?> addToCart(@RequestBody AddToCart addToCart)
      {
            try
            {
                  List<Cart> addedToCart=cartService.addProductToCart(addToCart);
                  return ResponseEntity.ok(addedToCart);
            }
            catch (Exception e)
            {
                  e.printStackTrace();
                  return ResponseEntity.badRequest().body(new Exception(e.getMessage()));
            }
      }
      @PostMapping("/removeProductFromCart")
      public ResponseEntity<?> removeCartwithProductId(@RequestBody RemoveFromCart removeFromCart) {
            try {
                  cartService.removeCartByUserIdAndProductId(removeFromCart);
                  return ResponseEntity.ok("Product removed from cart");
            }catch(Exception e) {
                  return ResponseEntity.badRequest().body(e.getMessage());
            }
      }
      @RequestMapping("getCartsByUserId/{id}")
      public ResponseEntity<?> getCartsByUserId(@PathVariable Long id) {
            try {
                  List<Cart> obj = cartService.getCartByUserId(id);
                  return ResponseEntity.ok(obj);
            }catch(Exception e) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Exception(e.getMessage()));
            }
      }

}
