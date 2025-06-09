package com.example.Invoice_Hrms.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class itemDto {


    private String itemName;
    private BigDecimal qty;
    private BigDecimal rate;
    private BigDecimal amount;

}
