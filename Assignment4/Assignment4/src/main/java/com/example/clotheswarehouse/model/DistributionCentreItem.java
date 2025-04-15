package com.example.clotheswarehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class DistributionCentreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    private DistributionCentre distributionCentre;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DistributionCentre getDistributionCentre() {
        return distributionCentre;
    }

    public void setDistributionCentre(DistributionCentre distributionCentre) {
        this.distributionCentre = distributionCentre;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Min(value = 0, message = "Quantity cannot be negative")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Quantity cannot be negative") int quantity) {
        this.quantity = quantity;
    }
}