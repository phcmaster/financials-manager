package br.com.fm.expensesmanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest webRequest) {
        var response = new GlobalExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            details.add(objectError.getDefaultMessage());
        }
        var erroResponse = new ValidationFieldsErrorResponse("Erro de validação no campo", details);
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleSystemException(Exception ex, MethodArgumentNotValidException exception, WebRequest webRequest) {
        var response = new GlobalExceptionResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
