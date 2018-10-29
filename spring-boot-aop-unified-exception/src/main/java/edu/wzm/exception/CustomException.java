package edu.wzm.exception;

public class CustomException extends Exception {

    private ErrorCode code;
    private String message;

    public CustomException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
        this.message = "";
    }

    public CustomException(ErrorCode code, String message) {
        super(code.getMessage());
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
