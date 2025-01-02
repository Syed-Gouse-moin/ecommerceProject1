package dev.syed.productservice.serivces;

import dev.syed.productservice.exceptions.ProductNotFoundException;
import dev.syed.productservice.exceptions.CategoryNotFoundException;
import dev.syed.productservice.models.Category;
import dev.syed.productservice.models.Product;
import dev.syed.productservice.repositories.categoryRepository;
import dev.syed.productservice.repositories.productRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class selfProductService implements ProductService {
    private categoryRepository categoryRepo;
    private productRepository productRepo;
    selfProductService(categoryRepository categoryRepo, productRepository productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> p = productRepo.findById(productId);
        if(p.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return p.get();
    }

    @Override
    public Product createProducts(String title, String image, String description, String category, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageurl(image);
        product.setPrice(price);

        Category categoryFromDatabase = categoryRepo.findByTitle(category);
        if(categoryFromDatabase == null) {
            Category cat = new Category();
            cat.setTitle(category);
            categoryFromDatabase = cat;
        }
        product.setCategory(categoryFromDatabase);
        Product savedProduct = productRepo.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getCategoryProducts(String category) {
        List<Product> products = productRepo.getProductsByCategoryTitle(category);
        return products;
    }

    @Override
    public Product DeleteProduct(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        productRepo.deleteById(productId);
        return product.get();
    }

    @Override
    public Product UpdateProduct(Long productId, String title, String image, String description, String category, double price) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> p = productRepo.findById(productId);
        if(p.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        Product product = p.get();
        if(title!=null){
            product.setTitle(title);
        }
        if(image!=null){
            product.setImageurl(image);
        }
        if(description!=null){
            product.setDescription(description);
        }
        if(category!=null){
            Category categoryFromDatabase = categoryRepo.findByTitle(category);
            if(categoryFromDatabase == null)
                throw new CategoryNotFoundException("Category not found, Please create new product and category");
            else {
                product.setCategory(categoryFromDatabase);
            }
        }
        if(price!=0){
            product.setPrice(price);
        }
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public List<String> getProductsByCategory() {
        List<Category> cat = categoryRepo.findAll();
        List<String> products = new ArrayList<>();
        for(Category cat1 : cat) {
            products.add(cat1.getTitle());
        }
        return products;
    }
}
