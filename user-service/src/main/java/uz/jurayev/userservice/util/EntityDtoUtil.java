package uz.jurayev.userservice.util;

import uz.jurayev.userservice.data.User;
import uz.jurayev.userservice.data.UserTransaction;
import uz.jurayev.userservice.dto.*;

import java.time.LocalDateTime;

public class EntityDtoUtil {

    public static User toUser(UserDtoResponse dtoMono){

        User user = new User();
        user.setUpdatedOn(LocalDateTime.now());
        user.setUserName(dtoMono.getUserName());
        user.setBalance(dtoMono.getBalance());
        return user;
    }
    public static UserDtoResponse toDto(User user){
        UserDtoResponse dto = new UserDtoResponse();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setBalance(user.getBalance());
        return dto;
    }

    public static UserDtoResponse toResponse(UserDtoRequest request){
        UserDtoResponse response = new UserDtoResponse();
        response.setUserName(request.getUserName());
        response.setBalance(request.getBalance());
        return response;
    }
    public static UserTransaction toUTransaction(TransactionRequest request){
        UserTransaction transaction = new UserTransaction();
        transaction.setUserId(request.getUserId());
        transaction.setAmount(request.getAmount());
        return transaction;
    }
    public static TransactionResponse toTResponse(TransactionRequest request, TransactionStatus status){
        TransactionResponse response = new TransactionResponse();
        response.setUserId(request.getUserId());
        response.setAmount(request.getAmount());
        response.setStatus(status);
        return response;
    }
}
