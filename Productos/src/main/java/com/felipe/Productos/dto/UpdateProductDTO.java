package com.felipe.Productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDTO {

  @NotBlank(message = "Product name cannot be blank")
  private String name;

  @NotBlank(message = "Product description cannot be blank")
  private String description;

  @NotBlank(message = "Product brand cannot be blank")
  private String brand;

  @Positive(message = "Product price must be greater than zero")
  @NotNull(message = "Product price cannot be null")
  private double price;

}
