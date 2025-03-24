package com.omagnect.inallmedia.controller;

import com.omagnect.inallmedia.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Test
    void getSortedProducts_shouldReturnSortedItems() throws Exception {
        // Given
        List<String> sortedItems = Arrays.asList("AItem", "BItem", "CItem");
        when(productService.sortProducts("price")).thenReturn(sortedItems);

        // When & Then
        mockMvc.perform(get("/products/sorted")
                        .param("field", "price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("AItem")))
                .andExpect(jsonPath("$[1]", is("BItem")))
                .andExpect(jsonPath("$[2]", is("CItem")));
    }

    @Test
    void getSortedProducts_shouldHandleEmptyList() throws Exception {
        // Given
        when(productService.sortProducts("price")).thenReturn(List.of());

        // When & Then
        mockMvc.perform(get("/products/sorted")
                        .param("field", "price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void getSortedProducts_shouldHandleDefaultField() throws Exception {
        // Given
        List<String> sortedItems = Arrays.asList("XItem", "YItem", "ZItem");
        when(productService.sortProducts("item")).thenReturn(sortedItems);

        // When & Then
        mockMvc.perform(get("/products/sorted")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("XItem")))
                .andExpect(jsonPath("$[1]", is("YItem")))
                .andExpect(jsonPath("$[2]", is("ZItem")));
    }
}