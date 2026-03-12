package com.kalpana.farmworktracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kalpana.farmworktracker.dto.FarmerDashboardDTO;
import com.kalpana.farmworktracker.dto.FarmerSummaryDTO;
import com.kalpana.farmworktracker.dto.PaymentDTO;
import com.kalpana.farmworktracker.dto.WorkDTO;
import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.entity.Work;
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
	
//	public List<FarmerDashboardDTO> getFarmersDashboard() {
//
//    List<Farmer> farmers = farmerRepository.findAll();
//
//    List<FarmerDashboardDTO> dashboardList = new ArrayList<>();
//
//    for (Farmer farmer : farmers) {
//
//        FarmerDashboardDTO dto = new FarmerDashboardDTO();
//
//        dto.setId(farmer.getId());
//        dto.setName(farmer.getName());
//        dto.setMobile(farmer.getMobile());
//
//        Double totalWork = workRepository.getTotalWorkAmount(farmer.getId());
//
//        if (totalWork == null) {
//            totalWork = 0.0;
//        }
//
//        dto.setTotalWork(totalWork);
//
//        dashboardList.add(dto);
//    }
//
//    return dashboardList;
//}
	
	public List<FarmerDashboardDTO> getFarmersDashboard() {

	    List<Farmer> farmers = farmerRepository.findAll();
	    List<FarmerDashboardDTO> dashboardList = new ArrayList<>();

	    for (Farmer farmer : farmers) {

	        FarmerDashboardDTO dto = new FarmerDashboardDTO();

	        dto.setId(farmer.getId());
	        dto.setName(farmer.getName());
	        dto.setMobile(farmer.getMobile());

	        Double totalWork = workRepository.getTotalWorkAmount(farmer.getId());
	        if (totalWork == null) totalWork = 0.0;

	        Double totalPayment = paymentRepository.getTotalPaymentAmount(farmer.getId());
	        if (totalPayment == null) totalPayment = 0.0;

	        Double pending = totalWork - totalPayment;
	        if (pending <0) pending = (double) 0;
	        dto.setTotalWork(totalWork);
	        dto.setTotalPayment(totalPayment);
	        dto.setPending(pending);

	        dashboardList.add(dto);
	    }

	    return dashboardList;
	}
	
	public FarmerSummaryDTO getSummary(Long farmerId) {
	    Farmer farmer = farmerRepository.findById(farmerId)
	                     .orElseThrow(() -> new RuntimeException("Farmer not found"));

	    Double totalWork = workRepository.getTotalWorkAmount(farmerId);
	    Double totalPayment = paymentRepository.getTotalPaymentAmount(farmerId);
	    if(totalWork == null) totalWork = 0.0;
	    if(totalPayment == null) totalPayment = 0.0;
	    
	    Double pending = totalWork - totalPayment;
	    if (pending < 0) pending = 0.0;
	    
	    FarmerSummaryDTO dto = new FarmerSummaryDTO();

	    dto.setName(farmer.getName());
	    dto.setTotalWork(totalWork);
	    dto.setTotalPayment(totalPayment);
	    dto.setPending(pending);

	    return dto;
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

    public WorkDTO addWork(WorkDTO dto) {

        Farmer farmer = farmerRepository.findById(dto.getFarmerId())
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        Work work = new Work();

        work.setFarmer(farmer);
        work.setWorkType(dto.getWorkType());
        work.setCropType(dto.getCropType());
        work.setUnitType(dto.getUnitType());
        work.setQuantity(dto.getQuantity());
        work.setRate(dto.getRate());
        work.setWorkDate(LocalDate.now());

        work = workRepository.save(work);

        WorkDTO response = new WorkDTO();
        response.setId(work.getId());
        response.setDate(work.getWorkDate().toString());
        response.setDescription(
                work.getWorkType() + " - " + work.getCropType() + " (" + work.getUnitType() + ")"
        );
        response.setAmount(work.getTotalAmount());

        return response;
    }
    
    
//    public Payment addPayment(Long farmerId, Payment payment) {
//
//        Farmer farmer = farmerRepository.findById(farmerId)
//                .orElseThrow(() -> new RuntimeException("Farmer not found"));
//        
//        Double totalWork = workRepository.getTotalWorkAmount(farmerId);
//        Double totalPayment = paymentRepository.getTotalPaymentAmount(farmerId);
//
//        if(totalWork == null) totalWork = 0.0;
//        if(totalPayment == null) totalPayment = 0.0;
//
//        if(totalWork == 0){
//            throw new RuntimeException("Cannot add payment. No work recorded.");
//        }
//
//        Double pending = totalWork - totalPayment;
//
//        if(pending <= 0){
//            throw new RuntimeException("No pending amount. Payment cannot be added.");
//        }
//        if(payment.getAmount() > pending){
//            throw new RuntimeException("Payment exceeds pending amount");
//        }
//        
//        payment.setFarmer(farmer);
//
//        return paymentRepository.save(payment);
//    }
    
    public Payment addPayment(Long farmerId, Payment payment) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));

        // Validate amount
        if (payment.getAmount() == null || payment.getAmount() <= 0) {
            throw new RuntimeException("Payment amount must be greater than zero");
        }

        // Validate payment date
        if (payment.getPaymentDate() == null) {
            throw new RuntimeException("Payment date is required");
        }

        if (payment.getPaymentDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Future payment date is not allowed");
        }

        Double totalWork = workRepository.getTotalWorkAmount(farmerId);
        Double totalPayment = paymentRepository.getTotalPaymentAmount(farmerId);

        if (totalWork == null) totalWork = 0.0;
        if (totalPayment == null) totalPayment = 0.0;

        if (totalWork == 0) {
            throw new RuntimeException("Cannot add payment. No work recorded.");
        }

        Double pending = totalWork - totalPayment;

        if (pending <= 0) {
            throw new RuntimeException("No pending amount. Payment cannot be added.");
        }

        if (payment.getAmount() > pending) {
            throw new RuntimeException("Payment exceeds pending amount");
        }

        payment.setFarmer(farmer);

        return paymentRepository.save(payment);
    }
    
    public List<WorkDTO> getWorkHistory(Long farmerId) {

        List<Work> works = workRepository.findByFarmerId(farmerId);

        return works.stream().map(work -> {

            WorkDTO dto = new WorkDTO();

            dto.setId(work.getId());
            dto.setFarmerId(work.getFarmer().getId());
            dto.setWorkType(work.getWorkType());
            dto.setCropType(work.getCropType());
            dto.setUnitType(work.getUnitType());
            dto.setQuantity(work.getQuantity());
            dto.setRate(work.getRate());

            dto.setDate(work.getWorkDate().toString());
            dto.setDescription(
                    work.getWorkType() + " - " +
                    work.getCropType() + " (" +
                    work.getUnitType() + ")"
            );

            dto.setAmount(work.getTotalAmount());

            return dto;

        }).toList();
    }
    
    public List<PaymentDTO> getPaymentHistory(Long farmerId) {
        List<Payment> payments = paymentRepository.findByFarmerId(farmerId);

        return payments.stream()
                       .map(payment -> new PaymentDTO(
                           payment.getId(),
                           payment.getPaymentDate().toString(),
                           payment.getAmount()
                       ))
                       .collect(Collectors.toList());
    }
    
    
}
