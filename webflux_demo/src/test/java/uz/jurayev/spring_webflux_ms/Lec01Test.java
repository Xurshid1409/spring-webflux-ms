package uz.jurayev.spring_webflux_ms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uz.jurayev.spring_webflux_ms.dto.request.MultiplyRequestDto;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;

public class Lec01Test extends AbstractTest{

    @Autowired
    WebClient webClient;

    @Test
    public void stepVerifierBlockTest(){
        ResponseModel block = this.webClient.get()
                .uri("/reactive/square/{input}", 6)
                .retrieve()
                .bodyToMono(ResponseModel.class)
                .block();

        System.out.println(block);
    }

    @Test
    public void stepVerifierReactiveTest(){
        Mono<ResponseModel> responseModelMono = this.webClient
                .get()
                .uri("/reactive/square/{input}", 8)
                .retrieve()
                .bodyToMono(ResponseModel.class);

        StepVerifier.create(responseModelMono)
                .expectNextMatches(e -> e.getOutput() == 64)
                .verifyComplete();
    }

    @Test
    public void fluxTest(){
        Flux<ResponseModel> responseModelFlux = this.webClient
                .get()
                .uri("/reactive/table/{input}", 8)
                .retrieve()
                .bodyToFlux(ResponseModel.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseModelFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void postTest(){
        Mono<ResponseModel> responseModelMono = this.webClient.post()
                .uri("/reactive/multiply")
                .bodyValue(requestDto(4, 65))
                .headers(h -> h.set("someKey", "someValue"))
                .retrieve()
                .bodyToMono(ResponseModel.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseModelMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDto requestDto(int x, int y){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirstNumber(x);
        dto.setSecondNumber(y);
        return dto;
    }
}
