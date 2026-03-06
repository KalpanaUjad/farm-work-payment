package com.kalpana.farmworktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.service.PaymentService;

@RestController
@RequestMapping("/api/farmers")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("/{farmerId}/payments")
    public Payment getPayment(@PathVariable Long farmerId) {
    	return paymentService.getPayment(farmerId);
    }

    @PostMapping("/{farmerId}/payments")
    public Payment addPayment(
            @PathVariable Long farmerId,
            @RequestBody Payment payment){

        return paymentService.addPayment(farmerId, payment);
    }
}