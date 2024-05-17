package ru.kirill.testformoisklad.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kirill.testformoisklad.exceptions.ProductNotAddException;
import ru.kirill.testformoisklad.exceptions.ProductNotChangeException;
import ru.kirill.testformoisklad.exceptions.ProductNotFoundException;
import ru.kirill.testformoisklad.models.DTOs.ErrorResp;
import ru.kirill.testformoisklad.models.DTOs.ProductDTO;
import ru.kirill.testformoisklad.models.Product;
import ru.kirill.testformoisklad.services.ProductStorage;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductStorage productStorage;

    @Autowired
    public ProductController(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @GetMapping
    public ResponseEntity<List<Product>> index(){
        return new ResponseEntity<>(productStorage.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(productStorage.get(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            ErrorResp resp = new ErrorResp(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public HttpEntity<?> add(@RequestBody @Valid ProductDTO productDTO,
                                      BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                StringBuilder stringBuilder = new StringBuilder();

                for (FieldError fieldError : bindingResult.getFieldErrors()) {
                    stringBuilder.append(fieldError.getField())
                            .append(" - ")
                            .append(fieldError.getDefaultMessage())
                            .append("; ");
                }
                throw new ProductNotAddException(stringBuilder.toString());
            }
        } catch (ProductNotAddException e){
            ErrorResp resp = new ErrorResp(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        productStorage.create(productDTO);
        return new HttpEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> change(@PathVariable("id") int id,
                                    @RequestBody @Valid ProductDTO productDTO,
                                    BindingResult bindingResult){

        try {
            if(bindingResult.hasErrors()){
                StringBuilder stringBuilder = new StringBuilder();

                for(FieldError fieldError : bindingResult.getFieldErrors()){
                    stringBuilder.append(fieldError.getField())
                            .append(" - ")
                            .append(fieldError.getDefaultMessage())
                            .append("; ");
                }
                throw new ProductNotChangeException(stringBuilder.toString());
            }

            productStorage.change(productDTO, id);
        } catch (ProductNotFoundException | ProductNotChangeException e) {
            ErrorResp resp = new ErrorResp(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        try {
            productStorage.delete(id);
        } catch (ProductNotFoundException e){
            ErrorResp resp = new ErrorResp(e.getMessage());
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
