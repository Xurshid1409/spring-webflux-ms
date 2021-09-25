package uz.jurayev.spring_webflux_ms.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.jurayev.spring_webflux_ms.dto.response.InputFailedValidationResponse;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException ex){
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());
        response.setInput(ex.getInput());
        return ResponseEntity.badRequest().body(response);
    }
}
