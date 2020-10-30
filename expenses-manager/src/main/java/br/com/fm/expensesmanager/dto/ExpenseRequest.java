package br.com.fm.expensesmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {

    @JsonProperty("expenseName")
    private String expenseName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dueDate")
    private String dueDate;

    @JsonProperty("value")
    private Double value;

    @JsonProperty("installment")
    private boolean installment;

    @JsonProperty("installmentTimes")
    private int installmentTimes;

}
