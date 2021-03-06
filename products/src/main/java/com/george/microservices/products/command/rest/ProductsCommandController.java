package com.george.microservices.products.command.rest;

import com.george.microservices.products.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductsCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String create(@RequestBody @Valid CreateProductRestModel request) {

        CreateProductCommand command = CreateProductCommand.builder()
                .title(request.getTitle())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        return commandGateway.sendAndWait(command);

//        String response = null;
//        try {
//            response = commandGateway.sendAndWait(command);
//        } catch (Exception e) {
//            response = e.getLocalizedMessage();
//        }
//
//        return response;
    }
//
//    @PutMapping
//    public String update() {
//        return "Hello from put product method!";
//    }
//
//    @DeleteMapping
//    public String delete() {
//        return "Hello from delete product method!";
//    }
}
