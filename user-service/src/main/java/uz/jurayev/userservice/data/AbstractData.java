package uz.jurayev.userservice.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;

import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public abstract class AbstractData implements Serializable {

    @Id
    private Long id;

    @CreatedDate
    private LocalDateTime createdOn;

    @LastModifiedDate
    @FutureOrPresent(message = "updated date must be in hte present or future")
    private LocalDateTime updatedOn;

}
