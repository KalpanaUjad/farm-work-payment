package com.kalpana.farmworktracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class WorkDTO {
	private Long farmerId;
	@NotBlank(message="Work type is required")
    private String workType;
    private String cropType;
    private String unitType;
    @Positive(message="quantity must be greater than 0")
    private Double quantity;
    private Double rate; 
    private Long id;
    private String date;
    private String description;
    private Double amount;

    public WorkDTO() {}

    public Long getFarmerId() { return farmerId; }
    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }

    public String getWorkType() { return workType; }
    public void setWorkType(String workType) { this.workType = workType; }

    public String getCropType() { return cropType; }
    public void setCropType(String cropType) { this.cropType = cropType; }

    public String getUnitType() { return unitType; }
    public void setUnitType(String unitType) { this.unitType = unitType; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public Double getRate() { return rate; }
    public void setRate(Double rate) { this.rate = rate; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

	
}