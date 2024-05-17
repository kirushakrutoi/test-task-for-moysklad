package ru.kirill.testformoisklad.repositories;

import org.springframework.stereotype.Repository;
import ru.kirill.testformoisklad.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductRepository {
    private static List<Product> products;
    private static int count;

    public ProductRepository() {
        products = new LinkedList<>();
        count = 0;
    }

    public List<Product> getAll() {
        return products;
    }

/*    static {
        products = new LinkedList<>();
        Product first = new Product(0, "test", "test", new BigDecimal(100), true);
        Product second = new Product(1, "computer", "description of computer", new BigDecimal(1000), true);
        Product third = new Product(2, "laptop", "test", new BigDecimal(750), false);
        Product fourth = new Product(3, "headphone", "description of headphone", new BigDecimal(200), true);
        products.add(first);
        products.add(second);
        products.add(third);
        products.add(fourth);
        count = 4;
    }*/

    public Product get(int id) {
        for(Product product : products){
            if(product.getId() == id)
                return product;
        }

        return null;
    }

    public void create(Product product) {
        product.setId(count++);
        products.add(product);
    }

    public void change(Product product){
        int index = getIndex(product.getId());
        products.remove(index);
        products.add(index, product);
    }

    public void delete(int id) {
        int index = getIndex(id);
        products.remove(index);
    }

    public int getIndex(int id){
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId() == id)
                return i;
        }

        return -1;
    }
}
