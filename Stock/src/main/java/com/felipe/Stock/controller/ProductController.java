package com.felipe.Stock.controller;

import com.felipe.Stock.dto.CreateProductDTO;
import com.felipe.Stock.dto.ProductResponseDTO;
import com.felipe.Stock.dto.UpdateProductDTO;
import com.felipe.Stock.entity.Product;
import com.felipe.Stock.mapper.IProductMapper;
import com.felipe.Stock.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    IProductService iProductService;

    @Autowired
    IProductMapper iProductMapper;

    @GetMapping(value = "/price")
    public ResponseEntity<List<ProductResponseDTO>> getProductsInRange(@RequestParam double min, @RequestParam double max){
        List<Product> productList = iProductService.getProductsInPriceRange(min, max);

        return ResponseEntity.ok().body(iProductMapper.listProductToListProductResponseDto(productList));
    }


    @GetMapping(value = "/name")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByNameOrDescription(@RequestParam String name){
        List<Product> productList = iProductService.getAllProductsByNameOrDescription(name);

        return ResponseEntity.ok().body(iProductMapper.listProductToListProductResponseDto(productList));
    }

    @GetMapping(value = "/ordered")
    public ResponseEntity<List<ProductResponseDTO>> getAllOrdered(){
        List<Product> productList = iProductService.getAllProductsOrdered();

        return ResponseEntity.ok().body(iProductMapper.listProductToListProductResponseDto(productList));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        List<Product> productList = iProductService.getAllProducts();

        return ResponseEntity.ok().body(iProductMapper.listProductToListProductResponseDto(productList));
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable("productId") Long id){
        Product product = iProductService.getProductById(id);

        return ResponseEntity.ok().body(iProductMapper.productToProductResponseDto(product));
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(@Validated @RequestBody CreateProductDTO createProductDTO){
        Product product = iProductMapper.createProductDtoToProduct(createProductDTO);

        Product savedProduct = iProductService.createProduct(product);

        ProductResponseDTO productResponseDTO = iProductMapper.productToProductResponseDto(savedProduct);

        return ResponseEntity.ok().body(productResponseDTO);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("productId") Long id, @Validated @RequestBody UpdateProductDTO updateProductDTO){
        Product product = iProductMapper.updateProductDtoToProduct(updateProductDTO);

        Product updatedProduct = iProductService.updateProduct(id, product);

        ProductResponseDTO productResponseDTO = iProductMapper.productToProductResponseDto(updatedProduct);

        return ResponseEntity.ok().body(productResponseDTO);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable("productId") Long id){
        iProductService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
