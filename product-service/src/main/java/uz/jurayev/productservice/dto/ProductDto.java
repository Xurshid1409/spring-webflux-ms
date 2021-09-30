package uz.jurayev.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String description;
    private double price;

    public ProductDto(String description, double price) {
        this.description = description;
        this.price = price;
    }
}
