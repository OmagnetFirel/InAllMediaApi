package com.omagnect.inallmedia.service;

import com.omagnect.inallmedia.model.Product;
import com.omagnect.inallmedia.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sortProducts_shouldReturnSortedAndDistinctItems() {
        // Given
        Product product1 = new Product("123", "BItem", "Category1", 150, 0, 10);
        Product product2 = new Product("456", "AItem", "Category2", 50, 10, 20);
        Product product3 = new Product("789", "CItem", "Category3", 250, 20, 30);
        List<Product> products = Arrays.asList(product1, product2, product3);

        when(productRepository.findAllSortedBy("price")).thenReturn(products);
        // When
        List<String> result = productService.sortProducts("price");

        // Then
        assertThat(result).containsExactly("AItem", "BItem", "CItem");
    }

    @Test
    void sortProducts_shouldHandleEmptyList() {
        // Given
        when(productRepository.findAllSortedBy("price")).thenReturn(List.of());

        // When
        List<String> result = productService.sortProducts("price");

        // Then
        assertThat(result).isEmpty();
    }


    @Test
    void createProduct_shouldSaveAndReturnProduct() {
        // Given
        Product productToCreate = new Product("123", "NewItem", "NewCategory", 100, 0, 50);
        when(productRepository.save(any(Product.class))).thenReturn(productToCreate);

        // When
        ResponseEntity createdProduct = productService.createProduct(productToCreate);

        // Then
        assertThat(createdProduct.getStatusCode()).isEqualTo(ResponseEntity.ok().build().getStatusCode());
        verify(productRepository).save(productToCreate);
    }

}
