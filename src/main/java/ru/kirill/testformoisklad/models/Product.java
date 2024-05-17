package ru.kirill.testformoisklad.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    @Size(min = 1, max = 255, message = "Non valid name")
    private String name;
    @Size(max = 4096, message = "Non valid description")
    private String description;
    @Min(value = 0, message = "The price should be less than 0")
    private BigDecimal price;
    private boolean inPresence;

    public Product(String name, String description, BigDecimal price, boolean inPresence) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inPresence = inPresence;
    }
}
