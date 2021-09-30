package uz.jurayev.userservice.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.userservice.data.UserTransaction;
import uz.jurayev.userservice.dto.TransactionRequest;
import uz.jurayev.userservice.dto.TransactionResponse;
import uz.jurayev.userservice.service.TransactionService;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @PostMapping
    public Mono<TransactionResponse> createTransaction(@RequestBody Mono<TransactionRequest> request){
        return request.flatMap(transactionService::createTransaction);
    }
    @GetMapping
    public Flux<UserTransaction> getUserById(@RequestParam("userId") Long userId){
        return transactionService.getUserById(userId);
    }
}
