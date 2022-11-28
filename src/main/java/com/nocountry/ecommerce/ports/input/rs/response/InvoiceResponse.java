package com.nocountry.ecommerce.ports.input.rs.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class InvoiceResponse {
   @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
   private LocalDateTime creationDate;
   private Float totalPrice;
   private List<SummaryResponse> productList;
}
