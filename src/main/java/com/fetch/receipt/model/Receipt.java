package com.fetch.receipt.model;


import java.util.List;


import org.springframework.lang.NonNull;


import jakarta.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Receipt {

    
    @NonNull
    private String retailer;
    @NonNull
    private String purchaseDate;
    @NonNull
    private String purchaseTime;

    private List<Items> items;

    @NonNull
    private String total;


}
