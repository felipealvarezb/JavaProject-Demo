package com.felipe.Productos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

  private Long productId;

  private String name;

  private String description;

  private String brand;

  private double price;

  private Date createdAt;

  private Date updatedAt;

}
