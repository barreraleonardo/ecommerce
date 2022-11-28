package com.nocountry.ecommerce.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

   @NotBlank
   private String name;

   @NotNull
   private Double price;

   @NotBlank
   private String detail;

   @NotBlank
   private String image;
}
