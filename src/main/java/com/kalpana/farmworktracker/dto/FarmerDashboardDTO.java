package com.kalpana.farmworktracker.dto;

public class FarmerDashboardDTO {

    private Long id;
    private String name;
    private String mobile;
    private Double pending;
    private Double totalWork;
    private Double totalPayment;
    
    public FarmerDashboardDTO() {
    }

	public FarmerDashboardDTO(Long id, String name, String mobile, Double pending, Double totalWork,
			Double totalPayment) {
		super();
		this.id = id;
		this.name = name;
		this.mobile = mobile;
		this.pending = pending;
		this.totalWork = totalWork;
		this.totalPayment = totalPayment;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public Double getTotalWork() {
		return totalWork;
	}

	public void setTotalWork(Double totalWork) {
		this.totalWork = totalWork;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getPending() {
        return pending;
    }

    public void setPending(Double pending) {
        this.pending = pending;
    }
}