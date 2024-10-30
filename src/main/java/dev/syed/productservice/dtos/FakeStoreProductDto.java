package dev.syed.productservice.dtos;

import dev.syed.productservice.models.Category;
import dev.syed.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    //to convert fakestore model to our model after fetching data from fakestore model
    // OR converting fakestore object to our model object
    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setImageurl(image);
        product.setDescription(description);
        product.setPrice(price);

        Category Productcategory = new Category();
        Productcategory.setTitle(category);

        product.setCategory(Productcategory);
        return product;
    }
}
