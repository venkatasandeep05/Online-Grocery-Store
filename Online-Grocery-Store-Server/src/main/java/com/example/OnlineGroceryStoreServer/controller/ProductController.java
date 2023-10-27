package com.example.OnlineGroceryStoreServer.controller;


import com.example.OnlineGroceryStoreServer.Exception.ProductNotFoundException;
import com.example.OnlineGroceryStoreServer.models.Products;
import com.example.OnlineGroceryStoreServer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController
{
    @Autowired
    ProductService productService;

   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/saveProducts")
    public ResponseEntity<?> saveProducts(@RequestBody List<Products> productsList)
    {
         return ResponseEntity.ok(productService.saveProducts(productsList));
    }

    @GetMapping("/getAllProducts")
    public List<Products> getAllProducts()
    {
       return productService.getAllProducts();
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name){
        try {
            return ResponseEntity.ok(productService.getProductByName(name));
        }
        catch (Exception e)
        {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException(name));
        }
    }
    @GetMapping("/getProductById/{id}}")
    public ResponseEntity<?> getProductById(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(productService.getProductById(id));
        }
        catch (Exception e)
        {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductNotFoundException("Product Not found"));
        }
    }
}
