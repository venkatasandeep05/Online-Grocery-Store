package com.example.OnlineGroceryStoreServer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveFromCart
{
    private Long userId;
    private Long productId;
}
