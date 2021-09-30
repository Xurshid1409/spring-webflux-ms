package uz.jurayev.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.productservice.dto.ProductDto;

import java.math.BigDecimal;

@Service
public class DataSetupService implements CommandLineRunner {

    private final ProductService service;

    @Autowired
    public DataSetupService(ProductService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

//        ProductDto p = new ProductDto("Lenovo legion y540", 1050.0);
//        ProductDto p2 = new ProductDto("iphone 12", 910.0);
//        ProductDto p3 = new ProductDto("samsung akg", 153.0);
//        Flux.just(p, p2, p3)
//                .flatMap(e -> this.service.createProduct(Mono.just(e)))
//                .subscribe(System.out::println);
    }
}
