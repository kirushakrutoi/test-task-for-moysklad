package ru.kirill.testformoisklad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirill.testformoisklad.exceptions.IncorrectRequestParamException;
import ru.kirill.testformoisklad.models.Product;
import ru.kirill.testformoisklad.services.ProductStorage;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products/search")
public class SearchProductController {

    @Autowired
    @Qualifier("productDbService")
    private ProductStorage productStorage;

    @GetMapping("")
    public ResponseEntity<?> get(@RequestParam Map<String, String> allParams){

        try {
            return new ResponseEntity<>(productStorage.getProductsWithFilter(allParams), HttpStatus.OK);
        } catch (IncorrectRequestParamException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
