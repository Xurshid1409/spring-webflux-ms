package uz.jurayev.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserDtoRequest {

    private String userName;
    private BigDecimal balance;
}
