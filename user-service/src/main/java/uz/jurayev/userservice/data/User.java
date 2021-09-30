package uz.jurayev.userservice.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Table(value = "users")
public class User extends AbstractData {

    private String userName;
    private BigDecimal balance;
}
