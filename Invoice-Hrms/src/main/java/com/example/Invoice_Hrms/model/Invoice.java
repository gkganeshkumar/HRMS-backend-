package com.example.Invoice_Hrms.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;



@Document(collection = "invoices")
@Data


public class Invoice {
    @Id

    private String id;

    @Indexed(unique = true)
    private String invoiceNo;

    private LocalDate invoiceDate;
   private String invoiceTerms;
    private LocalDate dueDate;

    private String invoiceCompanyName;
    private String invoiceCompanyAddress;
    private String invoiceCountry;
    private String invoicePinCode;
    private String invoiceEmail;
    private String invoiceMobileNo;
    private String invoiceConsultantName;
    private String invoiceCurrency;
    private String invoiceStatus;

    private String companyName;
    private String companyAddress;
    private String companyMobileNo;
    private String companyEmail;
    private String ThanksNote;
    @Transient
    private List<Item> items;
}
