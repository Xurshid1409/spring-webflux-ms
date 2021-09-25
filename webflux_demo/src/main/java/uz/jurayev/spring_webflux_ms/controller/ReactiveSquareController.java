package uz.jurayev.spring_webflux_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.spring_webflux_ms.dto.request.MultiplyRequestDto;
import uz.jurayev.spring_webflux_ms.dto.response.ResponseModel;
import uz.jurayev.spring_webflux_ms.exception.InputValidationException;
import uz.jurayev.spring_webflux_ms.service.ReactiveMathService;

import java.awt.*;
import java.util.Map;

@RestController
@RequestMapping("reactive")
public class ReactiveSquareController {

    private final ReactiveMathService reactiveMathService;

    @Autowired
    public ReactiveSquareController(ReactiveMathService reactiveMathService) {
        this.reactiveMathService = reactiveMathService;
    }

    @GetMapping("/square/{input}")
    public Mono<ResponseModel> findSquare(@PathVariable int input){
        if (input < 0)
            throw new InputValidationException(input);
        return reactiveMathService.findSquare(input);
    }

//    @GetMapping("/square/{input}")
//    public Mono<ResponseModel> findSquare2(@PathVariable Integer input){
//        return Mono.just(input)
//                .handle((integer, sink) -> {
//                    if (integer < 0)
//                        sink.error(new InputValidationException(integer));
//                    else sink.next(integer);
//                }).cast(Integer.class)
//                .flatMap(reactiveMathService::findSquare);
//    }

    @GetMapping(value = "/table/{input}")
    public Flux<ResponseModel> getTableSquare(@PathVariable int input){
        return reactiveMathService.tableSquare(input);
    }

    @GetMapping(value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ResponseModel> getTableSquareStream(@PathVariable int input){
        return reactiveMathService.tableSquare(input);
    }

    @PostMapping("/multiply")
    public Mono<ResponseModel> multiplyNumbers(@RequestBody Mono<MultiplyRequestDto> multiplyRequestDto,
                                               @RequestHeader Map<String, String> headers){
        System.out.println(headers);
        return reactiveMathService.multiplyNumbers(multiplyRequestDto);
    }
}
