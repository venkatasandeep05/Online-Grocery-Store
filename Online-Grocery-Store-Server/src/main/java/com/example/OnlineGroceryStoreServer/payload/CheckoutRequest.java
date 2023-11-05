package com.example.OnlineGroceryStoreServer.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest
{
    Long userId;
    Double price;
    String paymentType;
    String deliveryAddress;
    ArrayList<Long> productIds;
    String emailId;
}
