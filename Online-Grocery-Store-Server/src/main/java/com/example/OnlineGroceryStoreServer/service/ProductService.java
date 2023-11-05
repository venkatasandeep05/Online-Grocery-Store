package com.example.OnlineGroceryStoreServer.service;

import com.example.OnlineGroceryStoreServer.models.Products;
import com.example.OnlineGroceryStoreServer.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    ProductRepository productRepository;


    public List<Products> saveProducts(List<Products> productsList)
    {
            return productRepository.saveAll(productsList);
    }

    public List<Products> getAllProducts()
    {
       return productRepository.findAll();
    }

    public List<Products> getProductByName(String name) throws Exception
    {
      List<Products> productsList=productRepository.findByProductNameIgnoreCaseContaining(name);
      if(productsList.size()!=0)
          return productsList;
      else
          throw new Exception("product not found ");
    }

    public Products getProductById(Long id) throws Exception
    {
        Optional<Products> product=productRepository.findById(id);
        if(product!=null)
            return product.get();
        else
            throw new Exception("Not Found");

    }
}
