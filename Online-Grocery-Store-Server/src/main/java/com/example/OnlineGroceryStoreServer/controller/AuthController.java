package com.example.OnlineGroceryStoreServer.controller;

import com.example.OnlineGroceryStoreServer.models.Users;
import com.example.OnlineGroceryStoreServer.models.enm.ERole;
import com.example.OnlineGroceryStoreServer.payload.LoginRequest;
import com.example.OnlineGroceryStoreServer.payload.LoginResponse;
import com.example.OnlineGroceryStoreServer.payload.RegisterUserPayload;
import com.example.OnlineGroceryStoreServer.repo.RoleRepository;
import com.example.OnlineGroceryStoreServer.repo.UserRepository;
import com.example.OnlineGroceryStoreServer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil tokenProvider;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            newUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
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
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginRequest loginRequest) throws Exception
    {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateJwtToken(authentication);
            Users user=userRepository.findByEmail(loginRequest.getUserName());
            return ResponseEntity.ok(new LoginResponse(jwt,user.getName(),user.getEmail()));

        }
        catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect UserName or Password");
        }

    }
}
