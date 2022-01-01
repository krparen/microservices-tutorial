package com.george.microservices.products.core.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private Date date;
    private String message;
}
