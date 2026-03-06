package com.kalpana.farmworktracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kalpana.farmworktracker.entity.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
