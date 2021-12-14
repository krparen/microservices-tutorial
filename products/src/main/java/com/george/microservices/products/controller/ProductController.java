package com.george.microservices.products.controller;

import com.george.microservices.products.model.CreateProductRestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @GetMapping
    public static String get() {
        log.info("get product method called");
        return "Hello from get product method!";
    }

    @PostMapping
    public static String create(@RequestBody CreateProductRestModel request) {
        return "Hello from post product method! product title: " + request.getTitle();
    }

    @PutMapping
    public static String update() {
        return "Hello from put product method!";
    }

    @DeleteMapping
    public static String delete() {
        return "Hello from delete product method!";
    }
}
