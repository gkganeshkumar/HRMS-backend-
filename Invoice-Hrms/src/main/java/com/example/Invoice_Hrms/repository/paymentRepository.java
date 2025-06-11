package com.example.Invoice_Hrms.repository;

import com.example.Invoice_Hrms.model.payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface paymentRepository  extends MongoRepository<payment,String> {
}
