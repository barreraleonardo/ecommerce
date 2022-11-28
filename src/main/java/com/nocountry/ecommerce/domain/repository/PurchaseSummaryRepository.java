package com.nocountry.ecommerce.domain.repository;

import com.nocountry.ecommerce.domain.model.PurchaseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseSummaryRepository extends JpaRepository<PurchaseSummary, Long> {
}