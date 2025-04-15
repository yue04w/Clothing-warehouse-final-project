package com.example.clotheswarehouse.service;

import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.DistributionCentre;
import com.example.clotheswarehouse.model.DistributionCentreItem;
import com.example.clotheswarehouse.model.Item;
import com.example.clotheswarehouse.repository.DistributionCentreItemRepository;
import com.example.clotheswarehouse.repository.DistributionCentreRepository;
import com.example.clotheswarehouse.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionCentreService {
    private final DistributionCentreRepository dcRepository;
    private final ItemRepository itemRepository;
    private final DistributionCentreItemRepository dciRepository;

    public DistributionCentreService(DistributionCentreRepository dcRepository, ItemRepository itemRepository, DistributionCentreItemRepository dciRepository) {
        this.dcRepository = dcRepository;
        this.itemRepository = itemRepository;
        this.dciRepository = dciRepository;
    }

    public List<DistributionCentre> getAllDistributionCentres() {
        return dcRepository.findAll();
    }


    public void addItemToCentre(Long centreId, Item item, int quantity) {
        Optional<DistributionCentre> centreOpt = dcRepository.findById(centreId);
        if (centreOpt.isPresent()) {
            DistributionCentre centre = centreOpt.get();
            // Check if item already exists in centre
            Optional<DistributionCentreItem> existingDci = centre.getItems().stream()
                    .filter(dci -> dci.getItem().getName().equalsIgnoreCase(item.getName()) &&
                            dci.getItem().getBrand() == item.getBrand())
                    .findFirst();
            if (existingDci.isPresent()) {
                // Update quantity
                DistributionCentreItem dci = existingDci.get();
                dci.setQuantity(dci.getQuantity() + quantity);
                dciRepository.save(dci);
            } else {
                // Create new item and association
                Item savedItem = itemRepository.save(item);
                DistributionCentreItem dci = new DistributionCentreItem();
                dci.setDistributionCentre(centre);
                dci.setItem(savedItem);
                dci.setQuantity(quantity);
                dciRepository.save(dci);
            }
        }
    }

    public void removeItemFromCentre(Long centreId, Long itemId) {
        Optional<DistributionCentre> centreOpt = dcRepository.findById(centreId);
        if (centreOpt.isPresent()) {
            DistributionCentre centre = centreOpt.get();
            centre.getItems().removeIf(dci -> dci.getItem().getId().equals(itemId));
            dcRepository.save(centre);
        }
    }

    @Transactional
    public boolean requestItem(String name, Brand brand) {
        List<DistributionCentre> centres = dcRepository.findAll();
        for (DistributionCentre centre : centres) {
            Optional<DistributionCentreItem> matchedDci = centre.getItems().stream()
                    .filter(dci -> dci.getItem().getName().equalsIgnoreCase(name) &&
                            dci.getItem().getBrand() == brand &&
                            dci.getQuantity() > 0)
                    .findFirst();
            if (matchedDci.isPresent()) {
                DistributionCentreItem dci = matchedDci.get();
                // Update distribution centre quantity
                dci.setQuantity(dci.getQuantity() - 1);
                dciRepository.save(dci);
                // Update warehouse
                Optional<Item> warehouseItemOpt = itemRepository.findAll().stream()
                        .filter(item -> item.getName().equalsIgnoreCase(name) && item.getBrand() == brand)
                        .findFirst();
                Item warehouseItem;
                if (warehouseItemOpt.isPresent()) {
                    warehouseItem = warehouseItemOpt.get();
                    warehouseItem.setQuantity(warehouseItem.getQuantity() + 1);
                } else {
                    warehouseItem = new Item();
                    warehouseItem.setName(name);
                    warehouseItem.setBrand(brand);
                    warehouseItem.setYearOfCreation(2022); // Default
                    warehouseItem.setPrice(1000.01); // Minimum valid price
                    warehouseItem.setQuantity(1);
                }
                itemRepository.save(warehouseItem);
                return true;
            }
        }
        return false;
    }
}