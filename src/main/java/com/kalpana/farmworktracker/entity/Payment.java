package com.kalpana.farmworktracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate paymentDate;

    private String note;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;
    
    public Payment() {}

	public Payment(Long id, Double amount, LocalDate paymentDate, String note, Farmer farmer) {
		super();
		this.id = id;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.note = note;
		this.farmer = farmer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	
	

    
}
