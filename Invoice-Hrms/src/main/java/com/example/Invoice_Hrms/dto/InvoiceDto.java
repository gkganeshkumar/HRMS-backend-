package com.example.Invoice_Hrms.dto;

    import java.time.LocalDate;
import java.util.List;

import lombok.Data;
    import lombok.NonNull;


@Data

    public class InvoiceDto {


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

        private String companyName;
        private String companyAddress;
        private String companyMobileNo;
        private String companyEmail;

        private List<itemDto> items;
    }


