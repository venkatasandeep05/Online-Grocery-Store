package com.example.OnlineGroceryStoreServer.repo;

import com.example.OnlineGroceryStoreServer.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long>
{

    List<Orders> findByUserId(long user_id);
}
