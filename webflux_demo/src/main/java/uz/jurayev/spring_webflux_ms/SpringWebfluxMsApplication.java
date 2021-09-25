package uz.jurayev.spring_webflux_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class SpringWebfluxMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxMsApplication.class, args);
    }

}
