package com.george.microservices.orders.query;

import com.george.microservices.orders.core.data.OrderEntity;
import com.george.microservices.orders.core.data.OrderRepository;
import com.george.microservices.orders.core.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent, entity);
        orderRepository.save(entity);
    }
}
