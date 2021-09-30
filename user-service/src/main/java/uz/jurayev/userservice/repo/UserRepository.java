package uz.jurayev.userservice.repo;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import uz.jurayev.userservice.data.User;
import java.math.BigDecimal;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

   @Modifying
   @Query("update users set balance = users.balance - :amount where id = :userId and balance >= :amount")
   Mono<Boolean> updateUserBalance(Long userId, BigDecimal amount);
}
