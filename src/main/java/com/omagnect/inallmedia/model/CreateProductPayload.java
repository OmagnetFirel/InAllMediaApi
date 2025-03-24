package com.omagnect.inallmedia.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductPayload {
    private Product product;
    private boolean success;
    private String message;
}
