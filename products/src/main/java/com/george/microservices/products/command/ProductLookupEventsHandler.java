package com.george.microservices.products.command;

import com.george.microservices.products.core.data.ProductLookupEntity;
import com.george.microservices.products.core.data.ProductLookupRepository;
import com.george.microservices.products.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity entity = new ProductLookupEntity(
                productCreatedEvent.getProductId(),
                productCreatedEvent.getTitle()
        );

        productLookupRepository.save(entity);
    }
}
