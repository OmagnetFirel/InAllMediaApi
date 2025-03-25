package com.omagnect.inallmedia.graphql;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductInput {
    private String barcode;
    private String item;
    private String category;
    private Double price;
    private Double discount;
    private Integer available;
}
