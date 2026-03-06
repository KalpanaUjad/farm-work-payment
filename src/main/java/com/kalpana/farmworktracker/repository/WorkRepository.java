package com.kalpana.farmworktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kalpana.farmworktracker.entity.Payment;
import com.kalpana.farmworktracker.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work,Long>{
	@Query("SELECT SUM(w.totalAmount) FROM Work w WHERE w.farmer.id = :farmerId")
    Double getTotalWorkAmount(@Param("farmerId")Long farmerId);
}


