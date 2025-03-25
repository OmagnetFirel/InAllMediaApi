package com.omagnect.inallmedia.graphql;

import com.omagnect.inallmedia.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductPayload {
    private Product product;
    private boolean success;
    private String errorMessage;
}
