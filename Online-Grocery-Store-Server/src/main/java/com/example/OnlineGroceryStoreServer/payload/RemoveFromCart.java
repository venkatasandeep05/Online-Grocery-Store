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

    @Override
    public String toString() {
        return "RemoveFromCart{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
