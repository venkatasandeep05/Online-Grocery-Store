package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Role;
import com.example.OnlineGroceryStoreServer.models.Users;
import com.example.OnlineGroceryStoreServer.models.enm.ERole;
import com.example.OnlineGroceryStoreServer.payload.RegisterUserPayload;
import com.example.OnlineGroceryStoreServer.repo.RoleRepository;
import com.example.OnlineGroceryStoreServer.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
public class UserController
{

}
