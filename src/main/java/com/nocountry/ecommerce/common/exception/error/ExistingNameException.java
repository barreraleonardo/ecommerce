package com.nocountry.ecommerce.common.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExistingNameException extends RuntimeException {
    public ExistingNameException(String name) {
        super("There is a resource with the name: " + name);
    }
}