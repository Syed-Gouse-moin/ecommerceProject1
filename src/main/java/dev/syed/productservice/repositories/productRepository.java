package dev.syed.productservice.repositories;

import dev.syed.productservice.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface productRepository extends JpaRepository<Product,Long> {
    Product save(Product p);
    void deleteAllById(Long id);

    @Query(value = "SELECT p.* FROM Product p INNER JOIN Category c ON p.category_id = c.id WHERE c.title = :title", nativeQuery = true)
    List<Product> getProductsByCategoryTitle(@Param("title") String title);
}
