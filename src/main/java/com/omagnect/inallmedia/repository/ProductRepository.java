package com.omagnect.inallmedia.repository;

import com.omagnect.inallmedia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByPriceBetween(int initialRange, int finalRange);

    @Query("SELECT p FROM Product p WHERE p.item IN (SELECT DISTINCT p2.item FROM Product p2) ORDER BY " +
            "CASE WHEN :field = 'item' THEN p.item " +
            "WHEN :field = 'category' THEN p.category " +
            "WHEN :field = 'price' THEN p.price " +
            "WHEN :field = 'discount' THEN p.discount " +
            "WHEN :field = 'available' THEN p.available " +
            "ELSE p.barcode END")
    List<Product> findAllSortedBy(@Param("field") String field);
}
