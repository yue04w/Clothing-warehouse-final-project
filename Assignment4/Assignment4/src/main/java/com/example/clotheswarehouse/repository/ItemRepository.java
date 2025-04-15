package com.example.clotheswarehouse.repository;

import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Custom query for items by brand and year 2022
    @Query("SELECT i FROM Item i WHERE i.brand = :brand AND i.yearOfCreation = 2022")
    List<Item> findByBrandAndYear2022(@Param("brand") Brand brand);
}