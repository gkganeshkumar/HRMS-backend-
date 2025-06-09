package com.example.Invoice_Hrms.mapper;


import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "item")

public class Item {

    private String itemName;
    private BigDecimal qty;
    private BigDecimal rate;
    private BigDecimal amount;



    public Item(String itemName, BigDecimal qty, BigDecimal rate, BigDecimal amount) {
        this.itemName = itemName;
        this.qty = qty;
        this.rate = rate;
        this.amount = amount;
    }


}
