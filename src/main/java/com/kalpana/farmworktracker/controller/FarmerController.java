package com.kalpana.farmworktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.dto.FarmerDashboardDTO;
import com.kalpana.farmworktracker.dto.FarmerSummaryDTO;
import com.kalpana.farmworktracker.dto.PaymentDTO;
import com.kalpana.farmworktracker.dto.WorkDTO;
import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.service.FarmerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/farmers")
@CrossOrigin(origins = "http://localhost:5173")
public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    @PostMapping
    public ResponseEntity<Farmer> addFarmer(@Valid @RequestBody Farmer farmer){
        return ResponseEntity.ok(farmerService.addFarmer(farmer));
    }
    @GetMapping
    public List<FarmerDashboardDTO> getFarmersDashboard() {
        return farmerService.getFarmersDashboard();
    }
    
    

    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id){
        return farmerService.getFarmerById(id);
    }

    @PostMapping("/work")
    public ResponseEntity<WorkDTO> addWork(@Valid @RequestBody WorkDTO dto){
        return ResponseEntity.ok(farmerService.addWork(dto));
    }

    @GetMapping("/{farmerId}/summary")
    public FarmerSummaryDTO getSummary(@PathVariable Long farmerId){
        return farmerService.getSummary(farmerId);
    }

    @PostMapping("/{farmerId}/payments")
    public ResponseEntity<Payment> addPayment(
            @PathVariable Long farmerId,
            @Valid
            @RequestBody Payment payment){

        return ResponseEntity.ok(farmerService.addPayment(farmerId, payment));
    }

    @GetMapping("/payment/{farmerId}")
    public List<PaymentDTO> getPaymentHistory(@PathVariable Long farmerId) {
        return farmerService.getPaymentHistory(farmerId);
    }
    
  @GetMapping("/work/{farmerId}")
  public List<WorkDTO> getWorkHistory(@PathVariable Long farmerId) {
      return farmerService.getWorkHistory(farmerId);
  }
}