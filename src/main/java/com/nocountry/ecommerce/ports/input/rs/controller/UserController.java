package com.nocountry.ecommerce.ports.input.rs.controller;

import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.domain.usecase.UserService;
import com.nocountry.ecommerce.ports.input.rs.mapper.UserMapper;
import com.nocountry.ecommerce.ports.input.rs.request.UpdateUserRequest;
import com.nocountry.ecommerce.ports.input.rs.response.UserDetailResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.*;

@RequestMapping(USER_URI)
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    //=========================Update=========================//


    @ApiOperation("update user data")
    @PreAuthorize(BOTH)
    @PutMapping("{id}")
    public ResponseEntity<UserDetailResponse> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest userUpdate) {
        User user = userService.updateUser(id, userMapper.updateUserRequestToUser(userUpdate));
        return ResponseEntity.status(HttpStatus.OK).body(userMapper.userToUserDetailResponse(user));
    }

    //=========================Delete=========================//

    @ApiOperation("remove a user")
    @PreAuthorize(ADMIN)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
