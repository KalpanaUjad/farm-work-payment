package com.kalpana.farmworktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.service.PaymentService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/farmers")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @DeleteMapping("/payments/{paymentId}")
    public void deletePayment(@PathVariable Long paymentId){
        paymentService.deletePayment(paymentId);
    }
}