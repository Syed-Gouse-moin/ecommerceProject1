package dev.syed.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//DTO for each rqst bc in future if the rqst need additional parameters we can easily add
public class CreateProductRequestDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}
