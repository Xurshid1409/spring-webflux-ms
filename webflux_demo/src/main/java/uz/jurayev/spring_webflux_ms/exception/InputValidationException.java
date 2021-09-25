package uz.jurayev.spring_webflux_ms.exception;


public class InputValidationException extends RuntimeException{

    private static final String MSG = "input equals or greater than 0";
    private static final int ERROR_CODE = 100;
    private final int input;


    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }

    public int getInput() {
        return input;
    }
    public int getErrorCode(){
        return ERROR_CODE;
    }
}
