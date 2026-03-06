package com.kalpana.farmworktracker.dto;

public class FarmerSummaryDTO {

    private Double totalWork;

    private Double totalPayment;

    private Double pending;

    public FarmerSummaryDTO() {}
    
	public FarmerSummaryDTO(Double totalWork, Double totalPayment, Double pending) {
		super();
		this.totalWork = totalWork;
		this.totalPayment = totalPayment;
		this.pending = pending;
	}

	public Double getTotalWork() {
		return totalWork;
	}

	public void setTotalWork(Double totalWork) {
		this.totalWork = totalWork;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Double getPending() {
		return pending;
	}

	public void setPending(Double pending) {
		this.pending = pending;
	}

	
	

}