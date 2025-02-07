package com.fetch.receipt.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Items {

    @NonNull
    private String shortDescription;
    @NonNull
    private String price;

}
