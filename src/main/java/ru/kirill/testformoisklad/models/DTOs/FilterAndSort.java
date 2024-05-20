package ru.kirill.testformoisklad.models.DTOs;

import lombok.Data;

@Data
public class FilterAndSort {
    private String productName;
    private Integer price;
    private Integer lessPrice;
    private Integer morePrice;
    private Boolean productInPresence;
    private Boolean sortByName;
    private Boolean sortByPrice;
    private Integer n;
}
