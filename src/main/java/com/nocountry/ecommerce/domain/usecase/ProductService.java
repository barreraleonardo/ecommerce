package com.nocountry.ecommerce.domain.usecase;

import com.nocountry.ecommerce.domain.model.Product;
import com.nocountry.ecommerce.ports.input.rs.request.ProductFilterRequest;

import java.util.List;

public interface ProductService extends ActiveAvailable {

    Product getByIdIfExist(Long id);

    List<Product> findBySpecification(ProductFilterRequest request);

    Long create(Product product);

    void save(Product product);

    void update(Long id, Product product);

    void delete(Long id);

}
