package com.example.clotheswarehouse.model;

import com.example.clotheswarehouse.enums.Brand;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Brand is required")
    private Brand brand;

    @Min(value = 2022, message = "Year of creation must be after 2021")
    private int yearOfCreation;

    @DecimalMin(value = "1000.01", message = "Price must be greater than 1000")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    public Item() {
    }

    public Item(Long id, String name, Brand brand, int yearOfCreation, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.yearOfCreation = yearOfCreation;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "Brand is required") Brand getBrand() {
        return brand;
    }

    public void setBrand(@NotNull(message = "Brand is required") Brand brand) {
        this.brand = brand;
    }

    @Min(value = 2022, message = "Year of creation must be after 2021")
    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(@Min(value = 2022, message = "Year of creation must be after 2021") int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    @DecimalMin(value = "1000.01", message = "Price must be greater than 1000")
    public double getPrice() {
        return price;
    }

    public void setPrice(@DecimalMin(value = "1000.01", message = "Price must be greater than 1000") double price) {
        this.price = price;
    }

    @Min(value = 0, message = "Quantity cannot be negative")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 0, message = "Quantity cannot be negative") int quantity) {
        this.quantity = quantity;
    }
}