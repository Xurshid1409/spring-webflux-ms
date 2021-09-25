package uz.jurayev.spring_webflux_ms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Map;

public class Lec03RequestParam extends AbstractTest{

    @Autowired
    WebClient webClient;

    @Test
    public void paramsTest(){

        Map<String, Integer> map = Map.of(
                "page", 10,
                "count", 20
        );

        Flux<Integer> integerMono = this.webClient.get()
                .uri(b -> b.path("jobs/search").query("page={page}&count={count}").build(map))
                .retrieve()
                .bodyToFlux(Integer.class)
                .doOnNext(System.out::println);

        StepVerifier.create(integerMono)
                .expectNextCount(2)
                .verifyComplete();
    }
}
