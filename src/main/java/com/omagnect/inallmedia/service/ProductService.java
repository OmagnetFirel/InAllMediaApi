package com.omagnect.inallmedia.service;

import com.omagnect.inallmedia.model.Product;
import com.omagnect.inallmedia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> filterProductsByPrice(int initialRange, int finalRange){
        return productRepository.findByPriceBetween(initialRange, finalRange);
    }

    public List<String> sortProducts(String field){
        return productRepository.findAllSortedBy(field)
                .stream()
                .map(Product::getItem)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    public ResponseEntity createProduct(Product product){
        try {
            productRepository.save(product);
            return ResponseEntity.ok("Product created successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create product");
        }
    }

}
