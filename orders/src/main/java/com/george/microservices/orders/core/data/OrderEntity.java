package com.george.microservices.orders.core.data;

import com.george.microservices.orders.command.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderEntity implements Serializable {


    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
