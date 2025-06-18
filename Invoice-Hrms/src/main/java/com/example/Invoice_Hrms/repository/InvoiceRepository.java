package com.example.Invoice_Hrms.repository;

import com.example.Invoice_Hrms.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface InvoiceRepository extends MongoRepository<Invoice,String> {

    Optional<Invoice> findByInvoiceNo(String invoiceNo);
    boolean existsByInvoiceNo(String invoiceNo);
    void deleteByInvoiceNo(String invoiceNo);

}