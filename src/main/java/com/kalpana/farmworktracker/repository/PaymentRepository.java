package com.kalpana.farmworktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kalpana.farmworktracker.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	@Query("SELECT SUM(p.amount) FROM Payment p WHERE p.farmer.id = :farmerId")
    Double getTotalPaymentAmount(Long farmerId);
}
