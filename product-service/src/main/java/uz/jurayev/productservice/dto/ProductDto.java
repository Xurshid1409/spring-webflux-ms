package uz.jurayev.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDto {

    private String id;
    private String description;
    private BigDecimal price;
}
