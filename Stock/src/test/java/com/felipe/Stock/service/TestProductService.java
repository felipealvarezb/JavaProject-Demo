package com.felipe.Stock.service;

import com.felipe.Stock.entity.Product;
import com.felipe.Stock.repository.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestProductService {

  @Mock
  private IProductRepository iProductRepository;

  @InjectMocks
  private ProductServiceImpl iProductService;

  private Product product1;
  private Product product2;

  private List<Product> mockProducts;
  private String searchTerm;


  @BeforeEach
  void setUp() {
    product1 = new Product();
    product1.setProductId(1L);
    product1.setName("Producto 1");
    product1.setDescription("Descripción 1");
    product1.setBrand("Marca A");
    product1.setPrice(50.0);
    product1.setActive(true);

    product2 = new Product();
    product2.setProductId(2L);
    product2.setName("Producto 2");
    product2.setDescription("Descripción 2");
    product2.setBrand("Marca B");
    product2.setPrice(100.0);
    product2.setActive(true);

    mockProducts = Arrays.asList(product1, product2);
    searchTerm = "Coca Cola";
  }

  @Test
  void testGetProductsInPriceRange() {
    // Simulamos el comportamiento del repositorio
    when(iProductRepository.findProductsInPriceRange(40.0, 110.0))
            .thenReturn(Arrays.asList(product1, product2));

    // Llamamos al método del servicio
    List<Product> products = iProductService.getProductsInPriceRange(40.0, 110.0);

    // Verificamos los resultados
    assertNotNull(products);
    assertEquals(2, products.size());
    assertEquals("Producto 1", products.get(0).getName());
    assertEquals("Producto 2", products.get(1).getName());

    // Verificamos que el método del repositorio fue llamado correctamente
    verify(iProductRepository, times(1)).findProductsInPriceRange(40.0, 110.0);
  }

  @Test
  void testGetAllProductsByNameOrDescription() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.findByNameOrDescription(searchTerm)).thenReturn(mockProducts);

    // Ejecutamos el método del servicio
    List<Product> result = iProductService.getAllProductsByNameOrDescription(searchTerm);

    // Verificaciones
    verify(iProductRepository).findByNameOrDescription(searchTerm);
    assertEquals(2, result.size());
    assertEquals("Producto 1", result.get(0).getName());
    assertEquals("Producto 2", result.get(1).getName());
  }

  @Test
  void testGetAllProductsOrdered() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.findAllProductOrdered()).thenReturn(mockProducts);

    // Ejecutamos el método del servicio
    List<Product> result = iProductService.getAllProductsOrdered();

    // Verificaciones
    verify(iProductRepository).findAllProductOrdered();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("Producto 1", result.get(0).getName());
    assertEquals("Producto 2", result.get(1).getName());
  }

  @Test
  void testGetAllProducts() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.findAll()).thenReturn(mockProducts);

    // Ejecutamos el método del servicio
    List<Product> result = iProductService.getAllProducts();

    // Verificaciones
    verify(iProductRepository).findAll();
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals("Producto 1", result.get(0).getName());
    assertEquals("Producto 2", result.get(1).getName());
  }

  @Test
  void testGetProductById() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.findById(1L)).thenReturn(Optional.of(product1));

    // Ejecutamos el método del servicio
    Product result = iProductService.getProductById(1L);

    // Verificaciones
    verify(iProductRepository).findById(1L);
    assertNotNull(result);
    assertEquals("Producto 1", result.getName());
  }

  @Test
  void testCreateProduct() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.save(product1)).thenReturn(product1);

    // Ejecutamos el método del servicio
    Product result = iProductService.createProduct(product1);

    // Verificaciones
    verify(iProductRepository).save(product1);
    assertNotNull(result);
    assertEquals("Producto 1", result.getName());
  }

  @Test
  void testUpdateProduct() {
    // Simulamos que el producto ya existe en la base de datos
    when(iProductRepository.findById(1L)).thenReturn(Optional.of(product1));

    // Datos nuevos para la actualización
    Product newProductData = new Product();
    newProductData.setName("Producto Actualizado");
    newProductData.setDescription("Descripción Actualizada");
    newProductData.setBrand("Marca Nueva");
    newProductData.setPrice(75.0);

    // Simulamos que el reposiorio guarda y devuelve el producto actualizado
    Product updatedProduct = new Product(
            1L,
            "Producto Actualizado",
            "Descripción Actualizada",
            "Marca Nueva",
            75.0,
            true,
            new Date(), new Date());
    when(iProductRepository.save(any(Product.class))).thenReturn(updatedProduct);

    // Ejecutamos el método del servicio
    Product result = iProductService.updateProduct(1L, newProductData);

    // Verificaciones
    verify(iProductRepository).findById(1L);
    verify(iProductRepository).save(any(Product.class));

    assertNotNull(result);
    assertEquals("Producto Actualizado", result.getName());
    assertEquals("Descripción Actualizada", result.getDescription());
    assertEquals("Marca Nueva", result.getBrand());
    assertEquals(75.0, result.getPrice());
  }

  @Test
  void testDeleteProduct() {
    // Simulamos la respuesta del repositorio
    when(iProductRepository.findById(1L)).thenReturn(Optional.of(product1));

    // Ejecutamos el método del servicio
    iProductService.deleteProductById(1L);

    // Verificaciones
    verify(iProductRepository).findById(1L);
    verify(iProductRepository).delete(product1);
  }
}
