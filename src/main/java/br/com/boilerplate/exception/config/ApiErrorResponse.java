package br.com.boilerplate.exception.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApiErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private ZonedDateTime timestamp;

    private HttpStatus status;

    private String message;

    private String debugMessage;

    private List<String> errors;

    public ApiErrorResponse() {
        this.timestamp = ZonedDateTime.now();
    }

    public List<String> getErrors() {
        if (this.errors == null)
            this.errors = new ArrayList<>();

        return errors;
    }
}
