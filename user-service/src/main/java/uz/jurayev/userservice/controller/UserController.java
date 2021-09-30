package uz.jurayev.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.jurayev.userservice.dto.UserDtoRequest;
import uz.jurayev.userservice.dto.UserDtoResponse;
import uz.jurayev.userservice.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<UserDtoResponse> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<UserDtoResponse>> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    @Transactional
    public Mono<ResponseEntity<UserDtoResponse>> createUser(@RequestBody Mono<UserDtoRequest> userDtoMono){

        return userService.createUser(userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PutMapping("{id}")
    public Mono<ResponseEntity<UserDtoResponse>> updateUser(@PathVariable long id, @RequestBody Mono<UserDtoResponse> userDtoMono){
        return userService.updateUser(id, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public Mono<Void> deleteUser(@PathVariable Long id){
        return userService.removeUser(id);
    }

}
