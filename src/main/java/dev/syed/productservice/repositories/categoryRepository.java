package dev.syed.productservice.repositories;

import dev.syed.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String name);
    Category save(Category category);

}
