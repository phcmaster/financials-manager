package br.com.fm.expensesmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidationFieldsErrorResponse {

    private String message;
    private List<String> details;
}
