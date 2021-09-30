package uz.jurayev.productservice.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.jurayev.productservice.data.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Flux<Product> findByPriceBetween(double min, double max);
}
