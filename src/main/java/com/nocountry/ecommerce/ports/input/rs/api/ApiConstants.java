package com.nocountry.ecommerce.ports.input.rs.api;

public interface ApiConstants {
   String PRODUCT_URI = "/v1/product/";
   String AUTHENTICATION_URI = "/v1/auth/";
   String USER_URI = "/v1/users/";
   String MARK_URI = "/v1/mark/";
   String CATEGORY_URI = "/v1/category/";
   String INVOICE_URI = "/v1/invoice/";
   //authorization
   String ADMIN = "hasAnyRole('ROLE_ADMIN')";
   String PERMIT_ALL = "permitAll()";
   String BOTH = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')";
}
