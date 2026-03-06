package com.kalpana.farmworktracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.dto.FarmerSummaryDTO;
import com.kalpana.farmworktracker.entity.Farmer;
import com.kalpana.farmworktracker.service.FarmerService;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {
	@Autowired
    private FarmerService farmerService;
	
	@PostMapping
    public Farmer addFarmer(@RequestBody Farmer farmer){
        return farmerService.addFarmer(farmer);
    }

    @GetMapping
    public List<Farmer> getAllFarmers(){
        return farmerService.getAllFarmers();
    }

    @GetMapping("/{id}")
    public Farmer getFarmerById(@PathVariable Long id){
        return farmerService.getFarmerById(id);
    }
    
    @GetMapping("/{farmerId}/summary")
    public FarmerSummaryDTO getSummary(@PathVariable Long farmerId){
        return farmerService.getSummary(farmerId);
    }
}
