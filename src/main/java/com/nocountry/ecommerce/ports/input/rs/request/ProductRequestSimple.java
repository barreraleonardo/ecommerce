package com.nocountry.ecommerce.ports.input.rs.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestSimple {

    @NotNull
    private Long id;

    @NotNull
    private Integer amount;
}
