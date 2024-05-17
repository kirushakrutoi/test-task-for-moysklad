package ru.kirill.testformoisklad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kirill.testformoisklad.exceptions.ProductNotFoundException;
import ru.kirill.testformoisklad.models.DTOs.ProductDTO;
import ru.kirill.testformoisklad.models.Product;
import ru.kirill.testformoisklad.repositories.ProductRepository;
import ru.kirill.testformoisklad.util.Converter;

import java.util.List;

@Service
public class ProductService implements ProductStorage{

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Product get(int id) throws ProductNotFoundException {
        if(productRepository.get(id) == null){
            throw new ProductNotFoundException("Product with this id not found");
        }

        return productRepository.get(id);
    }

    public void create(ProductDTO productDTO){
        Product product = Converter.enrichProductDTO(productDTO);

        productRepository.create(product);
    }

    public void change(ProductDTO productDTO, int id) throws ProductNotFoundException {
        if(productRepository.getIndex(id) == -1){
            throw new ProductNotFoundException("Product with this id not found");
        }

        Product product = Converter.enrichProductDTO(productDTO, id);
        productRepository.change(product);
    }

    public void delete(int id) throws ProductNotFoundException {
        if(productRepository.getIndex(id) == -1){
            throw new ProductNotFoundException("Product with this id not found");
        }

        productRepository.delete(id);
    }
}
