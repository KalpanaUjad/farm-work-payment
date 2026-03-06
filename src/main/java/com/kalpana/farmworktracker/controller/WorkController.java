package com.kalpana.farmworktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalpana.farmworktracker.entity.Work;
import com.kalpana.farmworktracker.service.WorkService;

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

}
