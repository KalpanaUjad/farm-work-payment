package com.kalpana.farmworktracker.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Work;
import com.kalpana.farmworktracker.exception.FarmerNotFoundException;
import com.kalpana.farmworktracker.exception.InvalidWorkException;
import com.kalpana.farmworktracker.exception.WorkNotFoundException;
import com.kalpana.farmworktracker.repository.FarmerRepository;
import com.kalpana.farmworktracker.repository.PaymentRepository;
import com.kalpana.farmworktracker.repository.WorkRepository;

import jakarta.transaction.Transactional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private FarmerRepository farmerRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
    @Transactional
    public Work addWork(Long farmerId, Work work){
    	if (work.getWorkDate() == null) {
    	    throw new InvalidWorkException("Work date is required");
    	}
    	if(work.getWorkDate().isAfter(LocalDate.now())){
    	    throw new InvalidWorkException("Work date cannot be in the future");
    	}
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found"));

        work.setFarmer(farmer);
        work.setTotalAmount(work.getQuantity() * work.getRate());

        return workRepository.save(work);
    }

	public Work getWork(Long farmerId) {
		return workRepository.findById(farmerId).orElse(null);
	}
    
//    public List<Work> getWork(Long farmerId) {
//        return workRepository.findByFarmerId(farmerId);
//    }
    
//    public Work getWork(Long workId) {
//        return workRepository.findById(workId)
//                .orElseThrow(() ->
//                        new WorkNotFoundException("Work not found with id " + workId));
//    }
	
	@Transactional
	public void deleteWork(Long workId) {
	    if (!workRepository.existsById(workId)) {
	        throw new WorkNotFoundException("Work not found");
	    }
	    workRepository.deleteById(workId);
	}
	
	@Transactional
public Work updateWork(Long workId, Work updatedWork) {

    Work existingWork = workRepository.findById(workId)
            .orElseThrow(() -> new RuntimeException("Work not found"));

    // ✅ Validate fields
    if (updatedWork.getQuantity() == null || updatedWork.getQuantity() <= 0) {
        throw new RuntimeException("Quantity must be greater than zero");
    }

    if (updatedWork.getRate() == null || updatedWork.getRate() <= 0) {
        throw new RuntimeException("Rate must be greater than zero");
    }

    if (updatedWork.getWorkDate() == null) {
        throw new RuntimeException("Work date is required");
    }

    if (updatedWork.getWorkDate().isAfter(LocalDate.now())) {
        throw new RuntimeException("Future work date not allowed");
    }

    Long farmerId = existingWork.getFarmer().getId();

    Double totalWork = Optional.ofNullable(workRepository.getTotalWorkAmount(farmerId)).orElse(0.0);
    Double totalPayment = Optional.ofNullable(paymentRepository.getTotalPaymentAmount(farmerId)).orElse(0.0);

    // 🔥 calculate new total safely
    Double oldAmount = existingWork.getTotalAmount();
    Double newAmount = updatedWork.getQuantity() * updatedWork.getRate();

    Double newTotalWork = totalWork - oldAmount + newAmount;
    Double newPending = newTotalWork - totalPayment;

    // 🚨 CRITICAL CHECK
    if (newPending < 0) {
        throw new RuntimeException("Update invalid: payments exceed work amount");
    }

    // ✅ Update fields
    existingWork.setQuantity(updatedWork.getQuantity());
    existingWork.setRate(updatedWork.getRate());
    existingWork.setWorkDate(updatedWork.getWorkDate());
    existingWork.setWorkType(updatedWork.getWorkType());

    // totalAmount auto-calculated via @PreUpdate
    return workRepository.save(existingWork);
}
	

}