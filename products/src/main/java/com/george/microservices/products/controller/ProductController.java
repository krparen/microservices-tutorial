package com.george.microservices.products.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public static String get() {
        return "Hello from get product method!";
    }

    @PostMapping
    public static String create() {
        return "Hello from post product method!";
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
