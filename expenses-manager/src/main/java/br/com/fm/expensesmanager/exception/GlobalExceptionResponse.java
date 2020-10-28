package br.com.fm.expensesmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalExceptionResponse {

    private int statusCode;
    private String message;

}
