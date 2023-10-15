package com.example.OnlineGroceryStoreServer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products
{
    @Id
    private long id;
    private String productName;
    private double price;
    private String image;
    @Column(updatable=false)
    private Date productMfd;
    private int productStock;
    private String productRating;
    @Column(updatable=false)
    private Date productExp;
    private String productDesc;
}
