package com.kalpana.farmworktracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpana.farmworktracker.dto.FarmerSummaryDTO;
import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.repository.FarmerRepository;
import com.kalpana.farmworktracker.repository.PaymentRepository;
import com.kalpana.farmworktracker.repository.WorkRepository;

@Service
public class FarmerService {
	@Autowired
    private FarmerRepository farmerRepository;
	
	@Autowired
    private PaymentRepository paymentRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	public FarmerSummaryDTO getSummary(Long farmerId){

        
		Double totalWork = workRepository.getTotalWorkAmount(farmerId);
        Double totalPayment = paymentRepository.getTotalPaymentAmount(farmerId);

        if(totalWork == null){
            totalWork = 0.0;
        }

        if(totalPayment == null){
            totalPayment = 0.0;
        }

        Double pending = totalWork - totalPayment;

        FarmerSummaryDTO summary = new FarmerSummaryDTO();

        summary.setTotalWork(totalWork);
        summary.setTotalPayment(totalPayment);
        summary.setPending(pending);

        return summary;
    }
	
	public Farmer addFarmer(Farmer farmer) {
		return farmerRepository.save(farmer);
	}
	
	public List<Farmer> getAllFarmers(){
        return farmerRepository.findAll();
    }

    public Farmer getFarmerById(Long id){
        return farmerRepository.findById(id).orElse(null);
    }
}
