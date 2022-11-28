package com.nocountry.ecommerce.domain.repository;

import com.nocountry.ecommerce.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    boolean existsByName(String name);

    @Query("SELECT p FROM Product p WHERE p.isAvailable = true "
       + "and (?1 is null or p.name like %?1%) "
       + "and (?2 is null or p.mark.name like %?2%) "
       + "and (?3 is null or p.category.name like %?3%)"
    )
    List<Product> findByNameAndMarkAndCategory(String name, String mark, String category);
}
