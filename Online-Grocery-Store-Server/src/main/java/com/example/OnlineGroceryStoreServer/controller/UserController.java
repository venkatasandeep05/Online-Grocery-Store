package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Role;
import com.example.OnlineGroceryStoreServer.models.Users;
import com.example.OnlineGroceryStoreServer.models.enm.ERole;
import com.example.OnlineGroceryStoreServer.payload.RegisterUserPayload;
import com.example.OnlineGroceryStoreServer.repo.RoleRepository;
import com.example.OnlineGroceryStoreServer.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
public class UserController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterUserPayload registeredUser) {
        try
        {
            Users users=userRepository.findByEmail(registeredUser.getEmail());
            if(users!=null)
                return "User Already Exist";
            Users newUser=new Users();
            newUser.setName(registeredUser.getName());
            newUser.setEmail(registeredUser.getEmail());
            newUser.setPassword(registeredUser.getPassword());
            newUser.setRoles(new HashSet<>(Arrays.asList(roleRepository.findByName(ERole.ROLE_USER))));
            userRepository.save(newUser);
            return "User Registered Successfully";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "User registration is unsuccessfull";
        }
    }
}
