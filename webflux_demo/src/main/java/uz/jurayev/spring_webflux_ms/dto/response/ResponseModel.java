package uz.jurayev.spring_webflux_ms.dto.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ResponseModel {

    private LocalDate date = LocalDate.now();
    private double output;

    public ResponseModel(double output){
        this.output = output;
    }
}
