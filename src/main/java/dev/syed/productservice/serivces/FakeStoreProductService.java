package dev.syed.productservice.serivces;

import dev.syed.productservice.dtos.CategoriesOfProducts;
import dev.syed.productservice.dtos.FakeStoreProductDto;
import dev.syed.productservice.exceptions.ProductNotFoundException;
import dev.syed.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductService implements ProductService{

    //injecting resttemplate object in this class
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject("https://fakestoreapi.com/products/"
                +productId, FakeStoreProductDto.class );
        if(fakeStoreProductDto==null){
            throw new ProductNotFoundException("Product with id: " + productId + " doesn't exist. Retry some other product.");
        }
        return fakeStoreProductDto.toProduct();
    }
    public Product createProducts(String title,String image,String description,String category,double price){
        //converting our model data to dto type
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response =  restTemplate.postForObject(
                                    "https://fakestoreapi.com/products",
                                        fakeStoreProductDto,
                                        FakeStoreProductDto.class );

        return response.toProduct();
    }

    @Override
    public List<Product> getCategoryProducts(String category) {
        String url = "https://fakestoreapi.com/products/category/"+category;
        FakeStoreProductDto[] fakeStoreProductDto=restTemplate.getForObject(url, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : fakeStoreProductDto){
            products.add(dto.toProduct());
        }
        return products;
    }

    @Override
    public Product DeleteProduct(Long productId) {
        String url = "https://fakestoreapi.com/products/"+productId;
        FakeStoreProductDto fakeStoreProductDto = restTemplate.exchange( url,                        // URL for the request
                HttpMethod.DELETE,          // HTTP method DELETE
                null,                       // No request body needed
                FakeStoreProductDto.class   // Response type
        ).getBody();
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product UpdateProduct(Long productId, String title, String image, String description, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto= new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        FakeStoreProductDto response =  restTemplate.patchForObject(
                "https://fakestoreapi.com/products/"+productId,
                fakeStoreProductDto,
                FakeStoreProductDto.class );

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        String url = "https://fakestoreapi.com/products";
        FakeStoreProductDto[] fakeStoreProductDto=restTemplate.getForObject(url, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : fakeStoreProductDto){
            products.add(dto.toProduct());
        }
        return products;
    }

    @Override
    public List<String> getProductsByCategory() {
        String url = "https://fakestoreapi.com/products/categories";
        CategoriesOfProducts[] fakeStoreProductDto=restTemplate.getForObject(url, CategoriesOfProducts[].class);
        List<String> products = new ArrayList<>();
        for(CategoriesOfProducts dto : fakeStoreProductDto){
            products.add(dto.getName());
        }
        return products;
    }
}
