package br.com.fm.expensesmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

    private String expenseName;

    private String description;

    private LocalDate dueDate;

    private Double value;

    private boolean installment;

    private int installmentTimes;

}
