package com.example.Invoice_Hrms.model;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "items")

public class Item {


    private String itemName;
    private BigDecimal qty;
    private BigDecimal rate;
    private BigDecimal amount;

    private  String invoiceId;
}
