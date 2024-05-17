package ru.kirill.testformoisklad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.testformoisklad.models.Product;

@Repository
public interface ProductDbRepository extends JpaRepository<Product, Integer> {

}
