package uz.jurayev.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.productservice.dto.ProductDto;
import uz.jurayev.productservice.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public Flux<ProductDto> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("price-range")
    public Flux<ProductDto> getProductByPriceRange(@RequestParam("min") double min,
                                                   @RequestParam("max") double max){
        return productService.getProductByPrice(min, max);
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> insertProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.createProduct(productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDtoMono){
        return productService.updateProduct(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
