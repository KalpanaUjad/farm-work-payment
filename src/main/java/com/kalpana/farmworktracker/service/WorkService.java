package com.kalpana.farmworktracker.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    	if(work.getWorkDate().isAfter(LocalDate.now())){
    	    throw new RuntimeException("Work date cannot be in the future");
    	}
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        work.setFarmer(farmer);
        work.setTotalAmount(work.getQuantity() * work.getRate());

        return workRepository.save(work);
    }

	public Work getWork(Long farmerId) {
		return workRepository.findById(farmerId).orElse(null);
	}
	
	public void deleteWork(Long workId) {
	    if (!workRepository.existsById(workId)) {
	        throw new RuntimeException("Work not found");
	    }
	    workRepository.deleteById(workId);
	}

}