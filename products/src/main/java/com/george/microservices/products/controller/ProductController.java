package com.george.microservices.products.controller;

import com.george.microservices.products.command.CreateProductCommand;
import com.george.microservices.products.model.CreateProductRestModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;

    @GetMapping
    public static String get() {
        log.info("get product method called");
        return "Hello from get product method!";
    }

    @PostMapping
    public String create(@RequestBody CreateProductRestModel request) {

        CreateProductCommand command = CreateProductCommand.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        String response = commandGateway.sendAndWait(command);

        return response;
    }

    @PutMapping
    public String update() {
        return "Hello from put product method!";
    }

    @DeleteMapping
    public String delete() {
        return "Hello from delete product method!";
    }
}
