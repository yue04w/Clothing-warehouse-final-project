package com.example.clotheswarehouse.controller;

import com.example.clotheswarehouse.enums.Brand;
import com.example.clotheswarehouse.model.Item;
import com.example.clotheswarehouse.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String listItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort,
            Model model) {
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction sortDirection = sortParams[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Page<Item> itemPage = itemService.getAllItems(pageable);
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("totalItems", itemPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortParams[1]);
        model.addAttribute("isItemsEmpty", itemPage.isEmpty()); // Add this line
        return "list-items";
    }

    @GetMapping("/filter")
    public String filterItemsByBrandAndYear2022(@RequestParam("brand") Brand brand, Model model) {
        List<Item> filteredItems = itemService.getItemsByBrandAndYear2022(brand);
        model.addAttribute("items", filteredItems);
        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", 1);
        model.addAttribute("totalItems", filteredItems.size());
        model.addAttribute("sortField", "id");
        model.addAttribute("sortDirection", "asc");
        model.addAttribute("isItemsEmpty", filteredItems.isEmpty()); // Add this line
        return "list-items";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("item", new Item());
        return "add-item";
    }

    @PostMapping("/add")
    public String addItem(@Valid Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-item";
        }
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }
}