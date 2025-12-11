package com.univ.soa.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.soa.billingservice.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByStudentId(Long studentId);
}
