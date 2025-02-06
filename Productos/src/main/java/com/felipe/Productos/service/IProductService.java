package com.felipe.Productos.service;

import com.felipe.Productos.entity.Product;

import java.util.List;

public interface IProductService {


    List<Product> getProductsInPriceRange(double min, double max);
    List<Product> getAllProductsByNameOrDescription(String name);
    List<Product> getAllProductsOrdered();
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProductById(Long id);
}
