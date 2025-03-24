package com.omagnect.inallmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String barcode;
    private String item;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer available;
}
