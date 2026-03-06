package com.kalpana.farmworktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.repository.FarmerRepository;
import com.kalpana.farmworktracker.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(Long farmerId, Payment payment){

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        payment.setFarmer(farmer);

        return paymentRepository.save(payment);
    }

	public Payment getPayment(Long farmerId) {
		return paymentRepository.findById(farmerId).orElse(null);
	}
}