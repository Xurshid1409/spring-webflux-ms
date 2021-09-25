package uz.jurayev.productservice.util;

import org.springframework.beans.BeanUtils;
import uz.jurayev.productservice.data.Product;
import uz.jurayev.productservice.dto.ProductDto;

public class DataDtoUtil {

    public static ProductDto toDto(Product product){
        ProductDto dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }
    public static Product toData(ProductDto dto){
        Product data = new Product();
        BeanUtils.copyProperties(dto, data);
        return data;
    }
}
