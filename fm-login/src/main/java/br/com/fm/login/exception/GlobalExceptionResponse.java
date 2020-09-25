package br.com.fm.login.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalExceptionResponse {

    private String timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}
