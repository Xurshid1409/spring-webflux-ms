package uz.jurayev.spring_webflux_ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("jobs")
public class ParamsController {

    @GetMapping("/search")
    public Flux<Integer> search(@RequestParam("page") int page, @RequestParam("count") int count){
        return Flux.just(page, count);
    }
}
