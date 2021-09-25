package uz.jurayev.spring_webflux_ms.router.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.spring_webflux_ms.dto.request.MultiplyRequestDto;
import uz.jurayev.spring_webflux_ms.dto.response.InputFailedValidationResponse;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;
import uz.jurayev.spring_webflux_ms.exception.InputValidationException;
import uz.jurayev.spring_webflux_ms.service.ReactiveMathService;

@Service
public class MyRequestHandler {

    private final ReactiveMathService mathService;

    @Autowired
    public MyRequestHandler(ReactiveMathService mathService) {
        this.mathService = mathService;
    }

    public Mono<ServerResponse> squareHandler(ServerRequest request){
        int input = Integer.parseInt(request.pathVariable("input"));
        if(input < 0 || input > 100){
            return Mono.error(new InputValidationException(input));
        }
        Mono<ResponseModel> responseMono = mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, ResponseModel.class);

    }
    public Mono<ServerResponse> tableHandler(ServerRequest request){
        int input = Integer.parseInt(request.pathVariable("input"));
        if(input < 0 || input > 100){
            return Mono.error(new InputValidationException(input));
        }
        Flux<ResponseModel> responseFlux = mathService.tableSquare(input);
        return ServerResponse.ok().body(responseFlux, ResponseModel.class);
    }
    public Mono<ServerResponse> tableHandlerStream(ServerRequest request){
        int input = Integer.parseInt(request.pathVariable("input"));
        Flux<ResponseModel> responseFlux = mathService.tableSquare(input);
        return ServerResponse.ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(responseFlux, ResponseModel.class);
    }
    public Mono<ServerResponse> multiplyHandler(ServerRequest request){
        Mono<MultiplyRequestDto> multiplyRequestDtoMono = request.bodyToMono(MultiplyRequestDto.class);
        Mono<ResponseModel> responseMono = mathService.multiplyNumbers(multiplyRequestDtoMono);
        return ServerResponse.ok().body(responseMono, ResponseModel.class);
    }
}
