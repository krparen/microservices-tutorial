package com.george.microservices.orders.command.rest;

import com.george.microservices.orders.command.CreateOrderCommand;
import com.george.microservices.orders.command.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String createOrder(@RequestBody CreateOrderRestModel request) {

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .addressId(request.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();

        return commandGateway.sendAndWait(createOrderCommand);
    }
}
