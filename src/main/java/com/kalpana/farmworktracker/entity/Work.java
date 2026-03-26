package com.kalpana.farmworktracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workType;
    private String cropType;
    private String unitType;

    private Double quantity;
    private Double rate;

    private Double totalAmount;

    private LocalDate workDate;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @PrePersist
    @PreUpdate
    public void calculateTotalAmount(){
//        if(quantity != null && rate != null){
//            this.totalAmount = this.quantity * this.rate;
//        }
    	this.totalAmount=this.quantity * this.rate;
    }

	public Work() {
		super();
	}

	public Work(Long id, String workType, String cropType, String unitType, Double quantity, Double rate,
			Double totalAmount, LocalDate workDate, Farmer farmer) {
		super();
		this.id = id;
		this.workType = workType;
		this.cropType = cropType;
		this.unitType = unitType;
		this.quantity = quantity;
		this.rate = rate;
		this.totalAmount = totalAmount;
		this.workDate = workDate;
		this.farmer = farmer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getWorkDate() {
		return workDate;
	}

	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
    
    
}