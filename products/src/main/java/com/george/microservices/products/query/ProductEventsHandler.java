package com.george.microservices.products.query;

import com.george.microservices.core.events.ProductReservedEvent;
import com.george.microservices.products.core.data.ProductEntity;
import com.george.microservices.products.core.data.ProductsRepository;
import com.george.microservices.products.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@Slf4j
@RequiredArgsConstructor
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException ex) {
        log.error(ex.getMessage());
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception ex) throws Exception {
        throw ex;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productCreatedEvent, productEntity);

        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
        Integer newQuantity = productEntity.getQuantity() - productReservedEvent.getQuantity();
        productEntity.setQuantity(newQuantity);

        productsRepository.save(productEntity);

        log.info("ProductReservedEvent handler called on query side; orderId: {}, productId: {}",
                productReservedEvent.getOrderId(), productReservedEvent.getProductId());
    }
}
