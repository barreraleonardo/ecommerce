package com.nocountry.ecommerce.common.exception.error;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String email) {
        super("User with the email" + email + " already exists");
    }
}
