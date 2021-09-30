package uz.jurayev.userservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionResponse {

    private Long userId;
    private BigDecimal amount;
    private TransactionStatus status;
}
