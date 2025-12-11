package com.univ.soa.billingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univ.soa.billingservice.entity.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
