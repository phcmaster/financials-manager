package br.com.fm.expensesmanager.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idExpense;

    private String expenseName;

    private String description;

    private LocalDate dueDate;

    private Double value;

    private boolean installment;

    private int installmentTimes;

    private String userId;

}
