package com.nocountry.ecommerce.ports.input.rs.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {

    private String firstName;

    private String lastName;

    private String email;

}