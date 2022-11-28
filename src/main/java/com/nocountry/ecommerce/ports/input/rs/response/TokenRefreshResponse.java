package com.nocountry.ecommerce.ports.input.rs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshResponse {

    @NotEmpty
    @NotBlank
    private String jwtRefresh;
}
