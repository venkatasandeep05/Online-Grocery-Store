package com.example.OnlineGroceryStoreServer.repo;

import com.example.OnlineGroceryStoreServer.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long>
{

    List<Products> findByNameIgnoreCaseContaining(String name);


    Optional<Products> findById(Long id);
}
