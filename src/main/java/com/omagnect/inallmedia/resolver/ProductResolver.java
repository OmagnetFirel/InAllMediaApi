package com.omagnect.inallmedia.resolver;

import com.omagnect.inallmedia.graphql.CreateProductPayload;
import com.omagnect.inallmedia.graphql.ProductInput;
import com.omagnect.inallmedia.model.Product;
import com.omagnect.inallmedia.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolver {

    private final ProductService productService;

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

    @MutationMapping("createProduct")
    public CreateProductPayload  createProduct(@Argument("input") ProductInput product) {
        try{
            Product newProduct = Product.builder()
                    .item(product.getItem())
                    .price(product.getPrice())
                    .available(product.getAvailable())
                    .barcode(product.getBarcode())
                    .discount(product.getDiscount())
                    .build();
            productService.createProduct(newProduct);
            return new CreateProductPayload(newProduct, true ,"Product created successfully");
        } catch (Exception e) {
            return new CreateProductPayload(null, false,e.getMessage());
        }
    }
}
