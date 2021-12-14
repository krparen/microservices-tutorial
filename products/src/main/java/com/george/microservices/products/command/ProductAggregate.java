package com.george.microservices.products.command;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        // Validate CreateProductCommand
        if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be less or equal to zero");
        }

        if (createProductCommand.getTitle() == null
                || createProductCommand.getTitle().isBlank()) {
            throw new IllegalArgumentException("Price cannot be less or equal to zero");
        }
    }
}
