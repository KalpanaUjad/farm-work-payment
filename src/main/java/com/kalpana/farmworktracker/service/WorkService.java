package com.kalpana.farmworktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Work;
import com.kalpana.farmworktracker.repository.FarmerRepository;
import com.kalpana.farmworktracker.repository.WorkRepository;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    public Work addWork(Long farmerId, Work work){

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        work.setFarmer(farmer);
        work.setTotalAmount(work.getQuantity() * work.getRate());

        return workRepository.save(work);
    }

	public Work getWork(Long farmerId) {
		return workRepository.findById(farmerId).orElse(null);
	}

}