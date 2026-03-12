package com.kalpana.farmworktracker.dto;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")

public class FarmerSummaryDTO {

    private String name;
    private Double totalWork;
    private Double totalPayment;
    private Double pending;
    
    public FarmerSummaryDTO() {};

    public FarmerSummaryDTO(String name, Double totalWork, Double totalPayment, Double pending) {
        this.name = name;
        this.totalWork = totalWork;
        this.totalPayment = totalPayment;
        this.pending = pending;
    }

    public String getName() {
        return name;
    }

    public Double getTotalWork() {
        return totalWork;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public Double getPending() {
        return pending;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setTotalWork(Double totalWork) {
		this.totalWork = totalWork;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public void setPending(Double pending) {
		this.pending = pending;
	}
    
}