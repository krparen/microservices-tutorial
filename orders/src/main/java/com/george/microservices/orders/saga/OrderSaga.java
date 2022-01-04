package com.george.microservices.orders.saga;

import com.george.microservices.core.commands.ReserveProductCommand;
import com.george.microservices.core.events.ProductReservedEvent;
import com.george.microservices.orders.core.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {

        log.info("OrderSaga, handling OrderCreatedEvent..., event: {}", orderCreatedEvent);

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .orderId(orderCreatedEvent.getOrderId())
                .userId(orderCreatedEvent.getUserId())
                .build();

        log.info(
                "OrderSaga, OrderCreatedEvent handled; orderId: {}, productId: {}",
                orderCreatedEvent.getOrderId(),
                orderCreatedEvent.getProductId()
        );

        commandGateway.send(reserveProductCommand, new CommandCallback<ReserveProductCommand, Object>() {
            @Override
            public void onResult(
                    CommandMessage<? extends ReserveProductCommand> commandMessage,
                    CommandResultMessage<?> commandResultMessage) {

                if (commandResultMessage.isExceptional()) {
                    log.error("sending ReserveProductCommand failed, errorMessage: {}", commandResultMessage);
                    return;
                }

                log.info("sending ReserveProductCommand succeeded, command: {}", reserveProductCommand);
            }
        });

    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        log.info("OrderSaga: ProductReservedEvent is called for orderId = {} and productId = {}",
                productReservedEvent.getOrderId(), productReservedEvent.getProductId());
    }
}
