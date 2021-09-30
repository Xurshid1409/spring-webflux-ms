package uz.jurayev.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.productservice.dto.ProductDto;
import uz.jurayev.productservice.repo.ProductRepository;
import uz.jurayev.productservice.util.DataDtoUtil;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Flux<ProductDto> getAllProduct(){
        return repository.findAll()
                .map(DataDtoUtil::toDto);
    }

    public Mono<ProductDto> getProductById(String id){
        return repository.findById(id)
                .map(DataDtoUtil::toDto);
    }

    public Flux<ProductDto> getProductByPrice(double min, double max){
        return repository.findByPriceBetween(min, max)
                .map(DataDtoUtil::toDto);
    }

    public Mono<ProductDto> createProduct(Mono<ProductDto> dtoMono){
        return dtoMono.map(DataDtoUtil::toData)
                      .flatMap(repository::insert)
                      .map(DataDtoUtil::toDto);
    }
    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> dtoMono){
        return repository
                        .findById(id)
                        .flatMap(p -> dtoMono)
                        .map(DataDtoUtil::toData)
                        .doOnNext(e -> e.setId(id))
                        .flatMap(repository::save)
                        .map(DataDtoUtil::toDto);

    }
    public Mono<Void> deleteProduct(String id){
        return repository.deleteById(id);
    }
}
