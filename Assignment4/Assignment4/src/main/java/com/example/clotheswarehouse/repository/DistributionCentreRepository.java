package com.example.clotheswarehouse.repository;

import com.example.clotheswarehouse.model.DistributionCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionCentreRepository extends JpaRepository<DistributionCentre, Long> {
}