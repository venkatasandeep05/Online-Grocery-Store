package com.example.OnlineGroceryStoreServer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String emailId;
    private ArrayList<Long> productIds;
//    private ArrayList<Integer> quantity;
    private long userId;
    private String paymentType;
    private String deliveryAddress;
    private double price;
    private Date createdAt;
}
