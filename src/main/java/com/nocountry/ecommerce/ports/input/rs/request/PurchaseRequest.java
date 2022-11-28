package com.nocountry.ecommerce.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

    @NotEmpty
    @NotNull
    private List<ProductRequestSimple> listProducts;
}
