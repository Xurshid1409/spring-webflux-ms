package uz.jurayev.userservice.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.jurayev.userservice.data.UserTransaction;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Long> {

    Flux<UserTransaction> findByUserId(Long userId);
}
