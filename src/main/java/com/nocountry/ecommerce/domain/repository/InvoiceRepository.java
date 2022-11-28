package com.nocountry.ecommerce.domain.repository;

import com.nocountry.ecommerce.domain.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

   List<Invoice> findByUserId(Long id);
}
