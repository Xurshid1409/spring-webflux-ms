package uz.jurayev.spring_webflux_ms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uz.jurayev.spring_webflux_ms.dto.response.InputFailedValidationResponse;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;

public class Lec02BadRequest extends AbstractTest {

    @Autowired
    WebClient webClient;

    @Test
    public void badRequestTest() {
        Mono<ResponseModel> responseModelMono = this.webClient.get()
                .uri("reactive/square/{input}", -1)
                .retrieve()
                .bodyToMono(ResponseModel.class)
                .doOnNext(System.out::println)
                .doOnError(e -> System.out.println(e.getMessage()));

        StepVerifier.create(responseModelMono)
                .verifyError(WebClientResponseException.BadRequest.class);
    }

    @Test
    public void badRequestExchangeTest() {
        Mono<Object> objectMono = this.webClient.get()
                .uri("reactive/square/{input}", -1)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(e -> System.out.println(e.getMessage()));

        StepVerifier.create(objectMono)
                .expectNextCount(1)
                .verifyComplete();
    }
    private Mono<Object> exchange(ClientResponse clientResponse){
        if(clientResponse.rawStatusCode() == 400)
            return clientResponse.bodyToMono(InputFailedValidationResponse.class);
        else
            return clientResponse.bodyToMono(ResponseModel.class);
    }
}
