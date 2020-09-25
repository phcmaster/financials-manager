package br.com.fm.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({Exception.class, AuthenticationException.class})
    public ResponseEntity handlerException(Exception exception, WebRequest request) {

        GlobalExceptionResponse response = new GlobalExceptionResponse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        response.setTimestamp(LocalDateTime.now().format(formatter));
        response.setMessage(exception.getMessage());
        response.setError(request.getDescription(true));
        response.setPath(request.getContextPath());
        response.setStatusCode(500);

        if (exception instanceof Exception) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        if (exception instanceof AuthenticationException) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatusCode()));

    }

}
