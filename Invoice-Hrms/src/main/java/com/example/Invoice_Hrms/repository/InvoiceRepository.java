package com.example.Invoice_Hrms.repository;

import com.example.Invoice_Hrms.dto.InvoiceDto;
import com.example.Invoice_Hrms.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface InvoiceRepository extends MongoRepository<Invoice,String> {



}