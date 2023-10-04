package com.example.OnlineGroceryStoreServer.repo;

import com.example.OnlineGroceryStoreServer.models.Role;
import com.example.OnlineGroceryStoreServer.models.enm.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>
{

    Role findByName(ERole roleUser);

}
