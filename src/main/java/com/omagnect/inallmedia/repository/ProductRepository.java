package com.omagnect.inallmedia.repository;

import com.omagnect.inallmedia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByPriceBetween(int initialRange, int finalRange);

    @Query("SELECT p FROM Product p WHERE p.item IN (SELECT DISTINCT p2.item FROM Product p2) ORDER BY p.price")
    List<Product> findAllSortedByPrice();
}
