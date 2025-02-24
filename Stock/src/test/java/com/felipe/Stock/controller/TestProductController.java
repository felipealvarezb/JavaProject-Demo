package com.felipe.Stock.controller;

import com.felipe.Stock.dto.ProductResponseDTO;
import com.felipe.Stock.entity.Product;
import com.felipe.Stock.mapper.IProductMapper;
import com.felipe.Stock.service.IProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class TestProductController {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private IProductService iProductService;

  @MockitoBean
  private IProductMapper iProductMapper;

  @Test
  void testGetProductsInRange() throws Exception {
    // Datos simulados
    Product product1 = new Product(1L, "Producto 1", "Desc 1", "Marca A", 50.0, true, new Date(), new Date());
    Product product2 = new Product(2L, "Producto 2", "Desc 2", "Marca B", 100.0, true, new Date(), new Date());
    List<Product> productList = Arrays.asList(product1, product2);

    ProductResponseDTO productDTO1 = new ProductResponseDTO(1L, "Producto 1", "Desc 1", "Marca A", 50.0, new Date(), new Date());
    ProductResponseDTO productDTO2 = new ProductResponseDTO(2L, "Producto 2", "Desc 2", "Marca B", 100.0, new Date(), new Date());
    List<ProductResponseDTO> productDTOList = Arrays.asList(productDTO1, productDTO2);

    // Simular el comportamiento del servicio y mapper
    when(iProductService.getProductsInPriceRange(40.0, 110.0)).thenReturn(productList);
    when(iProductMapper.listProductToListProductResponseDto(productList)).thenReturn(productDTOList);

    // Ejecutar la petición HTTP
    mockMvc.perform(get("/api/v1/product/price")
                    .param("min", "40.0")
                    .param("max", "110.0")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()) // Verifica que el estado sea 200 OK
            .andExpect(jsonPath("$.size()").value(2)) // Verifica que la respuesta tiene 2 elementos
            .andExpect(jsonPath("$[0].name").value("Producto 1"))
            .andExpect(jsonPath("$[1].name").value("Producto 2"));

    // Verificar que los métodos fueron llamados
    verify(iProductService, times(1)).getProductsInPriceRange(40.0, 110.0);
    verify(iProductMapper, times(1)).listProductToListProductResponseDto(productList);
  }


}
