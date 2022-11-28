package com.nocountry.ecommerce.ports.input.rs.controller;

import com.nocountry.ecommerce.common.security.services.AuthenticationService;
import com.nocountry.ecommerce.domain.model.User;
import com.nocountry.ecommerce.domain.usecase.UserService;
import com.nocountry.ecommerce.ports.input.rs.mapper.AuthenticationMapper;
import com.nocountry.ecommerce.ports.input.rs.mapper.UserMapper;
import com.nocountry.ecommerce.ports.input.rs.request.AuthRequest;
import com.nocountry.ecommerce.ports.input.rs.request.RegisterRequest;
import com.nocountry.ecommerce.ports.input.rs.response.AuthResponse;
import com.nocountry.ecommerce.ports.input.rs.response.RegisterResponse;
import com.nocountry.ecommerce.ports.input.rs.response.TokenRefreshResponse;
import com.nocountry.ecommerce.ports.input.rs.response.UserDetailResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.AUTHENTICATION_URI;
import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.BOTH;

@RequestMapping(AUTHENTICATION_URI)
@RequiredArgsConstructor
@RestController
public class UserAuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final AuthenticationMapper authMapper;

    private final AuthenticationService authenticationService;


    //=========================Register=========================//

    @ApiOperation("register a new user")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {

        User createdUser = userService.createUser(userMapper.registerRequestToUser(registerRequest));
        UserDetailResponse userDetailResponse = userMapper.userToCreateUserResponse(createdUser);
        AuthResponse authResponse = authMapper
                .jwtToAuthResponse(authenticationService
                        .login(createdUser.getEmail(), registerRequest.getPassword()));

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(userDetailResponse, authResponse));
    }

    //=========================Login=========================//


    @ApiOperation("log into the ecommerce")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authMapper
                .jwtToAuthResponse(authenticationService
                        .login(authRequest.getEmail(), authRequest.getPassword()));
        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    //=========================Logout=========================//

    @ApiOperation("logout to session")
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    //=========================Refresh token=========================//

    @ApiOperation("get the refresh token")
    @PreAuthorize(BOTH)
    @GetMapping("/token-refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse();
        tokenRefreshResponse.setJwtRefresh(authenticationService.refresh(request, response));
        return ResponseEntity.status(HttpStatus.OK).body(tokenRefreshResponse);
    }


    @ApiIgnore
    @PreAuthorize(BOTH)
    @GetMapping("/me")
    public ResponseEntity<UserDetailResponse> getUserDetail(@AuthenticationPrincipal User user) {
        UserDetailResponse userDetailResponse = userMapper.userToUserDetailResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(userDetailResponse);
    }

}
