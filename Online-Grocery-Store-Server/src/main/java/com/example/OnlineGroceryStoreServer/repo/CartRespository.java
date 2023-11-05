package com.example.OnlineGroceryStoreServer.repo;

import com.example.OnlineGroceryStoreServer.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRespository extends JpaRepository<Cart, Long>
{

    Optional<List<Cart>> findByUserId(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);
}
