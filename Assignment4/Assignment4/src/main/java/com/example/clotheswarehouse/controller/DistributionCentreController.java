package com.example.clotheswarehouse.controller;

import com.example.clotheswarehouse.dto.DistributionCentreItemDto;
import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.Item;
import com.example.clotheswarehouse.service.DistributionCentreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/distribution-centres")
public class DistributionCentreController {
    private final DistributionCentreService dcService;

    public DistributionCentreController(DistributionCentreService dcService) {
        this.dcService = dcService;
    }

    @GetMapping
    public String listDistributionCentres(Model model) {
        model.addAttribute("centres", dcService.getAllDistributionCentres());
        model.addAttribute("item", new Item());
        return "admin-distribution-centres";
    }

    @GetMapping("/add-item")
    public String showAddItemForm(@RequestParam Long centreId, Model model) {
        DistributionCentreItemDto dto = new DistributionCentreItemDto();
        dto.setCentreId(centreId);
        dto.setItem(new Item());
        model.addAttribute("distributionCentreItem", dto);
        return "add-distribution-item";
    }

    @PostMapping("/add-item")
    public String addItemToCentre(@Valid @ModelAttribute("distributionCentreItem") DistributionCentreItemDto dto,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-distribution-item";
        }
        dcService.addItemToCentre(dto.getCentreId(), dto.getItem(), dto.getQuantity());
        return "redirect:/admin/distribution-centres";
    }

    @GetMapping("/delete-item")
    public String deleteItemFromCentre(@RequestParam Long centreId, @RequestParam Long itemId) {
        dcService.removeItemFromCentre(centreId, itemId);
        return "redirect:/admin/distribution-centres";
    }

    @PostMapping("/request-item")
    public String requestItem(@RequestParam String name, @RequestParam Brand brand) {
        boolean success = dcService.requestItem(name, brand);
        if (success) {
            return "redirect:/items";
        }
        return "redirect:/error?message=Stock%20can't%20be%20replenished";
    }
}