package com.kalpana.farmworktracker.dto;

public class PaymentDTO {
    private Long id;
    private String date;
    private Double amount;

    public PaymentDTO(Long id, String date, Double amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public Long getId() { return id; }
    public String getDate() { return date; }
    public Double getAmount() { return amount; }

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
    
}