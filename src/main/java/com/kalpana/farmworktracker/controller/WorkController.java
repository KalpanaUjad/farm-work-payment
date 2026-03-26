package com.kalpana.farmworktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.entity.Work;
import com.kalpana.farmworktracker.service.FarmerService;
import com.kalpana.farmworktracker.service.WorkService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/farmers")
public class WorkController {

    @Autowired
    private WorkService workService;

    @GetMapping("/{farmerId}/works")
    public Work getWork(@PathVariable Long farmerId) {
    	return workService.getWork(farmerId);
    }
    
    @PostMapping("/{farmerId}/works")
    public Work addWork(@PathVariable Long farmerId, @RequestBody Work work){
        return workService.addWork(farmerId, work);
    }
    @DeleteMapping("/work/{workId}")
    public void deleteWork(@PathVariable Long workId) {
        workService.deleteWork(workId);
    }
    
    @PutMapping("/works/{workId}")
    public ResponseEntity<Work> updateWork(
    	    @PathVariable Long workId,
    	    @Valid @RequestBody Work updatedWork
    	){
    	    return ResponseEntity.ok(workService.updateWork(workId, updatedWork));
    	}

}
