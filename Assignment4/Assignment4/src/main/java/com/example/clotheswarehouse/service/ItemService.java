package com.example.clotheswarehouse.service;

import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.DistributionCentreItem;
import com.example.clotheswarehouse.model.Item;
import com.example.clotheswarehouse.repository.DistributionCentreItemRepository;
import com.example.clotheswarehouse.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final DistributionCentreItemRepository distributionCentreItemRepository;

    public ItemService(ItemRepository itemRepository, DistributionCentreItemRepository distributionCentreItemRepository) {
        this.itemRepository = itemRepository;
        this.distributionCentreItemRepository = distributionCentreItemRepository;
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Page<Item> getAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public List<Item> getItemsByBrandAndYear2022(Brand brand) {
        return itemRepository.findByBrandAndYear2022(brand);
    }

    public void deleteItem(Long id) {
        Optional<Item> itemOpt = itemRepository.findById(id);
        if (itemOpt.isPresent()) {
            List<DistributionCentreItem> distributionCentreItemList = distributionCentreItemRepository.findByItem(itemOpt.get());
            if (!distributionCentreItemList.isEmpty()) {
                for (DistributionCentreItem distributionCentreItem : distributionCentreItemList) {
                    distributionCentreItem.setItem(null);
                    distributionCentreItem.setDistributionCentre(null);
                    distributionCentreItemRepository.save(distributionCentreItem);
                    distributionCentreItemRepository.delete(distributionCentreItem);
                }
            }
            itemRepository.delete(itemOpt.get());
        }
    }
}