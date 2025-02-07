package com.fetch.receipt.services;


import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fetch.receipt.model.Items;
import com.fetch.receipt.model.Receipt;

@Service
public class ReceiptService {
    private HashMap<String, Integer> receiptPoints = new HashMap<>();

    public String processReceipt(Receipt receipt){
        String id =  UUID.randomUUID().toString();
        receiptPoints.put(id, getTotalPoints(receipt));
        return id;
    }

    public Integer getPointsById(String id){
        return receiptPoints.get(id);
    }




    private Integer getTotalPoints(Receipt receipt){
        //Retailer name points
        int retailerPoint = receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        //Checking if total is a round dollar amount 
        int roundDollarAmountPoints = 0;
        double total = Double.parseDouble(receipt.getTotal());
        if(total == Math.floor(total)){
            roundDollarAmountPoints = 50;
        }

        //Checking if total is a multiple of 0.25
        int totalMultipleCheck = 0;
        if(total % 0.25 == 0){
            totalMultipleCheck = 25;
        }

        //Trimmed description check
        int trimmedPointsCheck = 0;
        for(Items items: receipt.getItems()){
            int trimmedDescriptionLength = items.getShortDescription().trim().length();
            if(trimmedDescriptionLength % 3 == 0){
                double point = Math.ceil(Double.parseDouble(items.getPrice()) * 0.2);
                trimmedPointsCheck += (int)point;
            }
        }

        //Check for every 2 items in the list
        int everyTwoItemsPoints  = 0;
        if(receipt.getItems().size() >= 2){
            everyTwoItemsPoints = (receipt.getItems().size() / 2) * 5;
        }

        //Check if purchase date is odd
        int isPurchaseDateOdd = 0;
        int date = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if(date % 2 != 0){
            isPurchaseDateOdd = 6;
        }

        //Check if purchase time occurs within certain time
        int timeCheckPoints = 0;
        int hour = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
        if(hour >= 14 && hour < 16){
            timeCheckPoints = 10;
        }
        return retailerPoint + roundDollarAmountPoints + totalMultipleCheck + trimmedPointsCheck + everyTwoItemsPoints + isPurchaseDateOdd + timeCheckPoints;

    }

}
