package uz.jurayev.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.userservice.data.UserTransaction;
import uz.jurayev.userservice.dto.TransactionRequest;
import uz.jurayev.userservice.dto.TransactionResponse;
import uz.jurayev.userservice.dto.TransactionStatus;
import uz.jurayev.userservice.repo.UserRepository;
import uz.jurayev.userservice.repo.UserTransactionRepository;
import uz.jurayev.userservice.util.EntityDtoUtil;

@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository transactionRepository;

    @Autowired
    public TransactionService(UserRepository userRepository, UserTransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }
    @Transactional
    public Mono<TransactionResponse> createTransaction(final TransactionRequest request){
        return userRepository.updateUserBalance(request.getUserId(), request.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toUTransaction(request))
                .flatMap(transactionRepository::save)
                .map(ut -> EntityDtoUtil.toTResponse(request, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toTResponse(request, TransactionStatus.DECLINED));
    }
    public Flux<UserTransaction> getUserById(Long userId){
        return transactionRepository.findByUserId(userId);
    }
}
