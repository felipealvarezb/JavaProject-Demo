package com.felipe.Stock.repository;

import com.felipe.Stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% or p.description LIKE %?1%")
  List<Product> findByNameOrDescription(String name);

  @Query("SELECT p FROM Product p ORDER BY p.name ASC")
  List<Product> findAllProductOrdered();

  @Query("SELECT p FROM Product p WHERE p.price >= :min AND p.price <= :max")
  List<Product> findProductsInPriceRange(@Param("min") double min, @Param("max") double max);


}
