package dev.syed.productservice.contollers;

import dev.syed.productservice.dtos.CreateProductRequestDto;
import dev.syed.productservice.models.Product;
import dev.syed.productservice.serivces.FakeStoreProductService;
import dev.syed.productservice.serivces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductContoller {

    //private ProductService productService = new FakeStoreProductService();
    // Dependency injection (IOC bc Framework is done behalf of us)
    private ProductService productService;
    public ProductContoller(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto requestDto) {
        return productService.createProducts(
                requestDto.getTitle(),
                requestDto.getImage(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice()
        );
    }
    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId){
        return productService.getSingleProduct(productId);
    }
    @GetMapping("/products/category/{cg}")
    public List<Product> getcategoryProducts(@PathVariable("cg") String category){
        return productService.getCategoryProducts(category);
    }
    @DeleteMapping("/products/{id}")
    public Product DeleteProduct(@PathVariable("id") Long productId){
        return productService.DeleteProduct(productId);
    }
}
