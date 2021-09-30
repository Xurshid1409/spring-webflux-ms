package uz.jurayev.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.userservice.dto.UserDtoRequest;
import uz.jurayev.userservice.dto.UserDtoResponse;
import uz.jurayev.userservice.repo.UserRepository;
import uz.jurayev.userservice.util.EntityDtoUtil;

@Service
public class UserService{

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Flux<UserDtoResponse> getAllUser(){
        return repository.findAll()
                .map(EntityDtoUtil::toDto);
    }
    @Transactional(readOnly = true)
    public Mono<UserDtoResponse> getUserById(Long userId){
        return repository.findById(userId)
                .map(EntityDtoUtil::toDto);
    }
    @Transactional
    public Mono<UserDtoResponse> createUser(Mono<UserDtoRequest> dto){
        return dto.map(EntityDtoUtil::toResponse)
                .map(EntityDtoUtil::toUser)
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);
    }
    @Transactional
    public Mono<UserDtoResponse> updateUser(Long userId, Mono<UserDtoResponse> userMono){
        return repository.findById(userId)
                .flatMap(u -> userMono.map(EntityDtoUtil::toUser)
                .doOnNext(e -> e.setId(userId)))
                .flatMap(this.repository::save)
                .map(EntityDtoUtil::toDto);
    }
    @Transactional(readOnly = true)
    public Mono<Void> removeUser(Long userId){
        return repository.existsById(userId).flatMap(e -> repository.deleteById(userId));
    }
}
