package com.omagnect.inallmedia.resolver;

import com.omagnect.inallmedia.model.CreateProductPayload;
import com.omagnect.inallmedia.model.Product;
import com.omagnect.inallmedia.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolver {

    private final ProductService productService;

    @Autowired
    public ProductResolver(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> allProducts() {
        return productService.getAllProducts();
    }


    @QueryMapping
    public List<Product> filterProductsByPrice(@Argument int minPrice, @Argument int maxPrice) {
        return productService.filterProductsByPrice(minPrice, maxPrice);
    }

    @QueryMapping
    public List<String> sortProducts(@Argument String field) {
        return productService.sortProducts(field);
    }

    @MutationMapping
    public CreateProductPayload  createProduct(@Argument Product product) {
        try{
            productService.createProduct(product);
            return new CreateProductPayload(product, true ,"Product created successfully");
        } catch (Exception e) {
            return new CreateProductPayload(product, false,e.getMessage());
        }
    }
}
