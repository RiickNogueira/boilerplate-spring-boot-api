package br.com.boilerplate.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private HttpStatus status;

    private static final long serialVersionUID = 1L;

    public CustomException(String msg) {
        super(msg);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CustomException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}