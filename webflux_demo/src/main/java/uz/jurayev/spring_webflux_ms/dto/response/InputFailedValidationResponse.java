package uz.jurayev.spring_webflux_ms.dto.response;

import lombok.Data;

@Data
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}
