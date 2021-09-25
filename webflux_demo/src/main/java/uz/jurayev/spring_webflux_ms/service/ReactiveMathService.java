package uz.jurayev.spring_webflux_ms.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.spring_webflux_ms.dto.request.MultiplyRequestDto;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<ResponseModel> findSquare(int input){
        return Mono.fromSupplier(() -> input * input)
                .map(ResponseModel::new);
    }

    public Flux<ResponseModel> tableSquare(int input){
      return Flux.range(1,10)
              .delayElements(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .map(i -> new ResponseModel(i * input));
    }
    public Mono<ResponseModel> multiplyNumbers(Mono<MultiplyRequestDto> multiplyRequestDto){
        return multiplyRequestDto.map(dto -> dto.getFirstNumber() * dto.getSecondNumber())
                .map(ResponseModel::new);
    }
}
