package com.felipe.Productos.service;

import com.felipe.Productos.entity.Product;
import com.felipe.Productos.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    IProductRepository iProductRepository;

    @Override
    public List<Product> getProductsInPriceRange(double min, double max) {
        return iProductRepository.findProductsInPriceRange(min, max);
    }

    @Override
    public List<Product> getAllProductsByNameOrDescription(String name) {
        return iProductRepository.findByNameOrDescription(name);
    }

    @Override
    public List<Product> getAllProductsOrdered() {
        return iProductRepository.findAllProductOrdered();
    }

    @Override
    public List<Product> getAllProducts() {
        return iProductRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Product existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return existingProduct;
    }

    @Override
    public Product createProduct(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());

        return iProductRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product existingProduct = iProductRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        iProductRepository.delete(existingProduct);
    }
}
