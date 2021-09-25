package uz.jurayev.productservice.repo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.jurayev.productservice.data.Product;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    @Query()
    Flux<Product> findByPriceBetween(BigDecimal priceGT, BigDecimal priceLT);
}
