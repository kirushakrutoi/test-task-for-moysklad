package ru.kirill.testformoisklad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.kirill.testformoisklad.models.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface ProductDbRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByNameStartingWith(String name);
    public List<Product> findByPriceGreaterThan(BigDecimal price);
    public List<Product> findByPriceLessThan(BigDecimal price);
    public List<Product> findByInPresence(boolean inPresence);
    public List<Product> findAllByOrderByName();
    public List<Product> findAllByOrderByPrice();

/*    @Query("""
       select product from products as product where
       (?1 is null or product.name like ?1)
       and (?2 is null or product.price = ?2)
       and (?3 is null or product.price < ?3)
       and (?4 is null or product.price > ?4)
       and (?5 is null or product.in_presence = ?5)
       """)
    List<Product> findFiltered(String productName,
                               Integer price,
                               Integer lessPrice,
                               Integer morePrice,
                               Boolean inPresence,
                               Boolean oderByName,
                               Boolean orderByPrice,
                               Integer n);*/

    /*       order by (?6 is null or product.name)
       (?7 is null or product.price)
       limit (?8 is null or ?8)*/

}
