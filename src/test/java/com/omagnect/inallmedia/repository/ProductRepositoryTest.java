package com.omagnect.inallmedia.repository;

import com.omagnect.inallmedia.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void findByPriceBetween_shouldReturnProductsInPriceRange() {
        Product product1 = new Product("123", "Item1", "Category1", 50, 0, 10);
        Product product2 = new Product("456", "Item2", "Category2", 150, 10, 20);
        Product product3 = new Product("789", "Item3", "Category3", 250, 20, 30);

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.flush();

        List<Product> foundProducts = productRepository.findByPriceBetween(100, 200);

        assertThat(foundProducts).hasSize(1);
        assertThat(foundProducts.get(0).getBarcode()).isEqualTo("456");
    }

    @Test
    void findAllSortedBy_shouldReturnProductsSortedBySpecifiedField() {
        Product product1 = new Product("123", "BItem", "Category1", 150, 0, 10);
        Product product2 = new Product("456", "AItem", "Category2", 50, 10, 20);
        Product product3 = new Product("789", "CItem", "Category3", 250, 20, 30);

        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.flush();

        List<Product> sortedProducts = productRepository.findAllSortedBy("price");

        assertThat(sortedProducts).hasSize(3);
        assertThat(sortedProducts.get(0).getItem()).isEqualTo("AItem");
        assertThat(sortedProducts.get(1).getItem()).isEqualTo("BItem");
        assertThat(sortedProducts.get(2).getItem()).isEqualTo("CItem");
    }
}
