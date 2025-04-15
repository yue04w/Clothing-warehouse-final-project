package com.example.clotheswarehouse.repository;

import com.example.clotheswarehouse.model.DistributionCentreItem;
import com.example.clotheswarehouse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionCentreItemRepository extends JpaRepository<DistributionCentreItem, Long> {
    List<DistributionCentreItem> findByItem(Item item);
}