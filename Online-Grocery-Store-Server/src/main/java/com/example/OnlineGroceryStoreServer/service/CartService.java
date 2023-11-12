package com.example.OnlineGroceryStoreServer.service;

import com.example.OnlineGroceryStoreServer.models.Cart;
import com.example.OnlineGroceryStoreServer.models.Products;
import com.example.OnlineGroceryStoreServer.payload.AddToCart;
import com.example.OnlineGroceryStoreServer.payload.RemoveFromCart;
import com.example.OnlineGroceryStoreServer.repo.CartRespository;
import com.example.OnlineGroceryStoreServer.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService
{
    @Autowired
    CartRespository cartRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Cart> addProductToCart(AddToCart addToCart) throws Exception
    {
        try {

            Products product = productRepository.findById(addToCart.getProductId()).
                    orElseThrow(() -> new Exception("Product Not Found"));
            Optional<Cart> cartExists=cartRepository.findByUserIdAndProductId(addToCart.getUserId(),addToCart.getProductId());
            if (product != null && cartExists.isEmpty()) {
                Cart cart = new Cart();
                cart.setProductId(addToCart.getProductId());
                cart.setUserId(addToCart.getUserId());
                cartRepository.save(cart);

            }
            return getCartByUserId(addToCart.getUserId());
        }
        catch (Exception e )
        {
            throw new RuntimeException(e.getMessage());
        }

    }

    public List<Cart> getCartByUserId(Long userId)
    {
        try {
            Optional<List<Cart>> cart = cartRepository.findByUserId(userId);
            if (cart.isPresent() && !cart.get().isEmpty())
                return cart.get();
            else
                throw new Exception("Cart is empty");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void removeCartByUserIdAndProductId(RemoveFromCart removeFromCart) throws Exception
    {
        Optional<Cart> cartExists=cartRepository.findByUserIdAndProductId(removeFromCart.getUserId(),removeFromCart.getProductId());
        if(cartExists.isEmpty())
            throw new Exception("Product Not found in users cart");
        try {
            cartRepository.deleteByUserIdAndProductId(removeFromCart.getUserId(), removeFromCart.getProductId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeAllCartByUserId(long userId)
    {
        cartRepository.deleteByUserId(userId);

    }

}
