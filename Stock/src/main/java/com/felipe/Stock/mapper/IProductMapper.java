package com.felipe.Stock.mapper;

import com.felipe.Stock.dto.CreateProductDTO;
import com.felipe.Stock.dto.ProductResponseDTO;
import com.felipe.Stock.dto.UpdateProductDTO;
import com.felipe.Stock.entity.Product;
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
