package ru.kirill.testformoisklad.util;

import ru.kirill.testformoisklad.models.DTOs.ProductDTO;
import ru.kirill.testformoisklad.models.Product;

public class Converter {
    public static Product enrichProductDTO(ProductDTO productDTO){
        return new Product(
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.isInPresence()
        );
    }

    public static Product enrichProductDTO(ProductDTO productDTO, int id){
        return new Product(
                id,
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.isInPresence()
        );
    }
}
