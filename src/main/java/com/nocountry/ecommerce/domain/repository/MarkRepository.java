package com.nocountry.ecommerce.domain.repository;

import com.nocountry.ecommerce.domain.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    boolean existsByName(String name);

    @Query(value = "SELECT m FROM Mark m WHERE m.isAvailable = true")
    List<Mark> findAllByIsAvailable();
}