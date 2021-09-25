package uz.jurayev.spring_webflux_ms.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uz.jurayev.spring_webflux_ms.dto.response.InputFailedValidationResponse;
import uz.jurayev.spring_webflux_ms.exception.InputValidationException;
import uz.jurayev.spring_webflux_ms.router.handler.MyRequestHandler;
import java.util.function.BiFunction;

@Configuration
public class RouterConfig {

    private final MyRequestHandler myRequestHandler;

    @Autowired
    public RouterConfig(MyRequestHandler myRequestHandler) {
        this.myRequestHandler = myRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> rootHandler(){
        return RouterFunctions.route()
                .path("route", this::requestHandler)
                .build();
    }

    private RouterFunction<ServerResponse> requestHandler(){
        return RouterFunctions.route()
                .GET("square/{input}", myRequestHandler::squareHandler)
                .GET("table/{input}", myRequestHandler::tableHandler)
                .onError(InputValidationException.class, exceptionHandler())
                .GET("table/{input}/stream", myRequestHandler::tableHandlerStream)
                .POST("multiply", myRequestHandler::multiplyHandler)
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException exception = (InputValidationException) err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setErrorCode(exception.getErrorCode());
            response.setMessage(exception.getMessage());
            response.setInput(exception.getInput());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
