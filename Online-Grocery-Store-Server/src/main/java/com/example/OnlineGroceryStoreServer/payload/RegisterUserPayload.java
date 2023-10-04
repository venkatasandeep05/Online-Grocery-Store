package com.example.OnlineGroceryStoreServer.payload;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserPayload
{
    private String name;
    private String email;
    private String password;

}
