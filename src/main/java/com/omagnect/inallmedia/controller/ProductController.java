package com.omagnect.inallmedia.controller;

import com.omagnect.inallmedia.model.Product;
import com.omagnect.inallmedia.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Filter products by price range", description = "Get a list of products within the specified price range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid price range supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No products found in the specified range",
                    content = @Content)
    })
    @GetMapping("/filter/price/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> filterProductsByPrice(
            @PathVariable int minPrice, @PathVariable int maxPrice
    ) {
        List<Product> products = productService.filterProductsByPrice(minPrice, maxPrice);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Sort products by a specified field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully sorted products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "No products found",
                    content = @Content)
    })
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<String>> sortProducts(
           @PathVariable @Parameter(description = "Field to sort by") String field){
        List<String> sortedProducts = productService.sortProducts(field);
        if (sortedProducts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sortedProducts);
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "No products found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Failed to create product",
                    content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        return  productService.createProduct(product);
    }

}
