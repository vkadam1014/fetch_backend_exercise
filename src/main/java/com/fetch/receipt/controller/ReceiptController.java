package com.fetch.receipt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.receipt.model.Receipt;
import com.fetch.receipt.services.ReceiptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    @Autowired
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt) {
        
        try{
            String receiptID = receiptService.processReceipt(receipt);
            HashMap<String, String> resultRespone = new HashMap<>();
            resultRespone.put("id", receiptID);
            return ResponseEntity.ok(resultRespone);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
        
    }


    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Integer>> getPoints(@PathVariable String id) {
        try{
            Integer point = receiptService.getPointsById(id);
            if(point == null){
                return ResponseEntity.notFound().build();
            }

            HashMap<String, Integer> points = new HashMap<>();
            points.put("points", point);
            return ResponseEntity.ok(points);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    



}
