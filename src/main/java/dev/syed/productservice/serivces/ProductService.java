package dev.syed.productservice.serivces;

import dev.syed.productservice.dtos.CategoriesOfProducts;
import dev.syed.productservice.exceptions.ProductNotFoundException;
import dev.syed.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    Product createProducts(String title,String image,String description,String category,double price);
    List<Product> getCategoryProducts(String category);
    Product DeleteProduct(Long productId);
    Product UpdateProduct(Long productId,String title,String image,String description,String category,double price);
    List<Product> getAllProducts();
    List<String> getProductsByCategory();
}
