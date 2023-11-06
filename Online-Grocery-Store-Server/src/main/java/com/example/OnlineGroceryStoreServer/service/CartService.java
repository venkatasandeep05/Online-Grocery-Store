package com.example.OnlineGroceryStoreServer.service;

import com.example.OnlineGroceryStoreServer.models.Cart;
import com.example.OnlineGroceryStoreServer.models.Products;
import com.example.OnlineGroceryStoreServer.payload.AddToCart;
import com.example.OnlineGroceryStoreServer.payload.RemoveFromCart;
import com.example.OnlineGroceryStoreServer.repo.CartRespository;
import com.example.OnlineGroceryStoreServer.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            if (product != null) {
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

    public void removeCartByUserIdAndProductId(RemoveFromCart removeFromCart) throws Exception
    {
        try {
            cartRepository.deleteByUserIdAndProductId(removeFromCart.getUserId(), removeFromCart.getProductId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeAllCartByUserId(long userId)
    {
        cartRepository.deleteByUserId(userId);

    }

}
