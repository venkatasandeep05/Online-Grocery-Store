package com.example.OnlineGroceryStoreServer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse
{
    private String jwt;
    private String name;
    private String userName;
}
