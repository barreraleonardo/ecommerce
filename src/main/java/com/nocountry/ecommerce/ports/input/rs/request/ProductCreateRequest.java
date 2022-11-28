package com.nocountry.ecommerce.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

   @NotBlank
   private String name;

   @NotNull
   private Double price;

   @NotNull
   private Long stock;

   @NotBlank
   private String detail;

   @NotBlank
   private String image;

   @NotNull
   private Long mark;

   @NotNull
   private Long category;
}
