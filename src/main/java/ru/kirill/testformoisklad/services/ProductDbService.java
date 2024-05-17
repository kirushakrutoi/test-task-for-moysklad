package ru.kirill.testformoisklad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.kirill.testformoisklad.exceptions.ProductNotFoundException;
import ru.kirill.testformoisklad.models.DTOs.ProductDTO;
import ru.kirill.testformoisklad.models.Product;
import ru.kirill.testformoisklad.repositories.ProductDbRepository;
import ru.kirill.testformoisklad.util.Converter;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDbService implements ProductStorage{
    @Autowired
    private ProductDbRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(int id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with this id not found");
        }
        return product.get();
    }

    @Override
    public void create(ProductDTO productDTO) {
        Product product = Converter.enrichProductDTO(productDTO);
        productRepository.save(product);
    }

    @Override
    public void change(ProductDTO productDTO, int id) throws ProductNotFoundException {
        Product product = Converter.enrichProductDTO(productDTO, id);

        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product with this id not found");
        }

        productRepository.save(product);
    }

    @Override
    public void delete(int id) throws ProductNotFoundException {
        if(productRepository.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product with this id not found");
        }
        productRepository.deleteById(id);
    }
}