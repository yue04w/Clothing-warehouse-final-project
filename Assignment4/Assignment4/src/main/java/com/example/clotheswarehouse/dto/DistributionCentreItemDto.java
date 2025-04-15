package com.example.clotheswarehouse.dto;

import com.example.clotheswarehouse.model.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DistributionCentreItemDto {
    @NotNull(message = "Centre ID is required")
    private Long centreId;

    @Valid
    private Item item;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public @NotNull(message = "Centre ID is required") Long getCentreId() {
        return centreId;
    }

    public void setCentreId(@NotNull(message = "Centre ID is required") Long centreId) {
        this.centreId = centreId;
    }

    public @Valid Item getItem() {
        return item;
    }

    public void setItem(@Valid Item item) {
        this.item = item;
    }

    @Min(value = 1, message = "Quantity must be at least 1")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "Quantity must be at least 1") int quantity) {
        this.quantity = quantity;
    }
}