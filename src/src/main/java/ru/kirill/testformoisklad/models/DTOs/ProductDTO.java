package ru.kirill.testformoisklad.models.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Size(min = 1, max = 255, message = "Non valid name")
    private String name;
    @Size(max = 4096, message = "Non valid description")
    private String description;
    @Min(value = 0, message = "The price should be less than 0")
    private BigDecimal price;
    private boolean inPresence;
}