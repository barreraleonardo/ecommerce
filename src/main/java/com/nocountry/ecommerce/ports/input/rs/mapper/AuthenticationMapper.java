package com.nocountry.ecommerce.ports.input.rs.mapper;

import com.nocountry.ecommerce.common.security.utils.Jwt;
import com.nocountry.ecommerce.ports.input.rs.response.AuthResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticationMapper {

    AuthResponse jwtToAuthResponse(Jwt jwt);
}
