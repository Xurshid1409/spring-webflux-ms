package uz.jurayev.userservice.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserTransaction extends AbstractData{

    private Long userId;
    private BigDecimal amount;

}
