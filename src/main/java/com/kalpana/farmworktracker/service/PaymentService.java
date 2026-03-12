package com.kalpana.farmworktracker.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.repository.FarmerRepository;
import com.kalpana.farmworktracker.repository.PaymentRepository;
import com.kalpana.farmworktracker.repository.WorkRepository;

@Service
public class PaymentService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private WorkRepository workRepository;

    public Payment addPayment(Long farmerId, Payment payment){
    	if(payment.getPaymentDate().isAfter(LocalDate.now())){
    	    throw new RuntimeException("Payment date cannot be in the future");
    	}
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
        
        Double totalWork = workRepository.getTotalWorkAmount(farmerId);
        Double totalPayment = paymentRepository.getTotalPaymentAmount(farmerId);

        if (totalWork == null) totalWork = 0.0;
        if (totalPayment == null) totalPayment = 0.0;

        Double pending = totalWork - totalPayment;

        // 🚨 Critical validation
        if(payment.getAmount() > pending){
            throw new RuntimeException("Payment exceeds pending amount");
        }

        
        payment.setFarmer(farmer);

        return paymentRepository.save(payment);
    }

	public Payment getPayment(Long farmerId) {
		return paymentRepository.findById(farmerId).orElse(null);
	}
	
	public void deletePayment(Long paymentId){

	    if(!paymentRepository.existsById(paymentId)){
	        throw new RuntimeException("Payment not found");
	    }

	    paymentRepository.deleteById(paymentId);
	}
}