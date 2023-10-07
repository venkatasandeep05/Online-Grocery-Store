package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Role;
import com.example.OnlineGroceryStoreServer.models.Users;
import com.example.OnlineGroceryStoreServer.models.enm.ERole;
import com.example.OnlineGroceryStoreServer.repo.RoleRepository;
import com.example.OnlineGroceryStoreServer.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class WelcomeController
{
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @GetMapping("/welcome")
    public String setDefaultValuesInDB()
    {
        try{
            createDefaultRoles();
            createDefaultUsers();
            return "Welcome to Online-Grocery-Store please login or signup now!";
        }catch (Exception e)
        {
            return "Something went wrong";
        }
    }

    private void createDefaultRoles()
    {
        if(roleRepository.findAll().size()==0)
        {

            ERole roles[] = ERole.values();
            for (ERole role : roles) {
                Role r = new Role(role);
                roleRepository.save(r);
            }
            roleRepository.flush();
        }
    }

    private void createDefaultUsers()
    {
        Users user=userRepository.findByEmail(adminEmail);
        if(user==null)
        {
            Users users = new Users();
            users.setName("Admin");
            users.setEmail(adminEmail);
            users.setPassword(passwordEncoder.encode(adminPassword));
            users.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(users);
        }

    }
}
