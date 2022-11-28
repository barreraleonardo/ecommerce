package com.nocountry.ecommerce.common.exception.error;

public class RoleNotFoundException extends RuntimeException{
   public RoleNotFoundException(String name) {
      super("Role not found with the name: " + name);
   }
}
