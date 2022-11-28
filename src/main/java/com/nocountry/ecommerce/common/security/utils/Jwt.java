package com.nocountry.ecommerce.common.security.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jwt {

    private String jwt;

    private String jwtRefresh;
}
