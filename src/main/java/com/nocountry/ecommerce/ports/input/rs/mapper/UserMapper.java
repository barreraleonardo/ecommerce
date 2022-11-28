package com.nocountry.ecommerce.ports.input.rs.mapper;

import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.ports.input.rs.request.RegisterRequest;
import com.nocountry.ecommerce.ports.input.rs.request.UpdateUserRequest;
import com.nocountry.ecommerce.ports.input.rs.response.UserDetailResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerRequestToUser(RegisterRequest registerRequest);

    UserDetailResponse userToCreateUserResponse(User user);

    UserDetailResponse userToUserDetailResponse(User user);

    User updateUserRequestToUser(UpdateUserRequest updateUserRequest);
}