package com.george.microservices.orders.core.events;

import com.george.microservices.orders.command.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
