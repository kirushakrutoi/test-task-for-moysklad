package ru.kirill.testformoisklad.services;

import ru.kirill.testformoisklad.exceptions.ProductNotFoundException;
import ru.kirill.testformoisklad.models.DTOs.ProductDTO;
import ru.kirill.testformoisklad.models.Product;

import java.util.List;

public interface ProductStorage {
    public List<Product> getAll();
    public Product get(int id) throws ProductNotFoundException;
    public void create(ProductDTO productDTO);
    public void change(ProductDTO productDTO, int id) throws ProductNotFoundException;
    public void delete(int id) throws ProductNotFoundException;

}