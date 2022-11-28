package com.nocountry.ecommerce.ports.input.rs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class SummaryResponse {
   private Integer amount;
   private ProductDetails product;
}
