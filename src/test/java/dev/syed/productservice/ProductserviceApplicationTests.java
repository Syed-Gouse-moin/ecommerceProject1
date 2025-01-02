package dev.syed.productservice;

import dev.syed.productservice.models.Category;
import dev.syed.productservice.models.Product;
import dev.syed.productservice.repositories.categoryRepository;
import dev.syed.productservice.repositories.productRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class ProductserviceApplicationTests {
    @Autowired
    private categoryRepository categoryRepo;
    @Autowired
    private productRepository productRepo;

    @Test
    void testQuery(){
        Category cat = categoryRepo.findById(1L).get();
        System.out.println(cat.getId());
        System.out.println("We are done here");

        List<Product> currentProduct = cat.getProducts();
        System.out.println(currentProduct.size());

        System.out.println("We got the products as well");

    }
    @Test
    void contextLoads() {
    }

}
