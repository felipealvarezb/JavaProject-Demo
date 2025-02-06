package com.felipe.Productos.mapper;

import com.felipe.Productos.dto.CreateProductDTO;
import com.felipe.Productos.dto.ProductResponseDTO;
import com.felipe.Productos.dto.UpdateProductDTO;
import com.felipe.Productos.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IProductMapper {

  @Mapping(target = "productId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Product createProductDtoToProduct(CreateProductDTO createProductDTO);

  @Mapping(target = "productId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Product updateProductDtoToProduct(UpdateProductDTO updateProductDTO);

  ProductResponseDTO productToProductResponseDto(Product product);

  List<ProductResponseDTO> listProductToListProductResponseDto(List<Product> productList);
}
