package com.kalpana.farmworktracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.entity.Work;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	List<Payment> findByFarmerId(Long farmerId);

    // Custom query: sum of all payments for a farmer
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.farmer.id = :farmerId")
    Double getTotalPaymentAmount(@Param("farmerId") Long farmerId);
}
