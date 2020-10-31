package br.com.fm.expensesmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseResponse {

    private Long idExpense;

    private String expenseName;

    private String description;

    private LocalDate dueDate;

    private Double value;

    private boolean installment;

    private int installmentTimes;

}
