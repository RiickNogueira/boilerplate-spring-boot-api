package br.com.boilerplate.exception.config;

import br.com.boilerplate.exception.AuthorizationException;
import br.com.boilerplate.exception.CustomException;
import br.com.boilerplate.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandling.class);

    @ExceptionHandler(CustomException.class)
    ResponseEntity<Object> handleCustomException(CustomException exception) {
        LOGGER.error("Custom Exception: ", exception);
        if(exception.getStatus() == null) exception.setStatus(HttpStatus.BAD_REQUEST);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(exception.getStatus());

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(AuthorizationException.class)
    ResponseEntity<Object> handleAuthorizationException(AuthorizationException exception) {
        LOGGER.error("Authorization Exception: ", exception);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(HttpStatus.UNAUTHORIZED);

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception) {
        LOGGER.error("Object Not Found Exception: ", exception);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        LOGGER.error("Access Denied Exception: ", exception);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        apiErrorResponse.setMessage(exception.getMessage());
        apiErrorResponse.setStatus(HttpStatus.UNAUTHORIZED);

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler
    ResponseEntity<Object> unknownException(final Exception exception, HttpServletResponse response) {
        LOGGER.error("Unknown Exception: ", exception);

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
        String message = "Aconteceu um erro inesperado durante a operação. Visualizar log.";
        apiErrorResponse.setMessage(message);
        apiErrorResponse.setDebugMessage(exception.getMessage());
        apiErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return buildResponseEntity(apiErrorResponse);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse apiErrorResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(apiErrorResponse, headers, apiErrorResponse.getStatus());
    }

}
