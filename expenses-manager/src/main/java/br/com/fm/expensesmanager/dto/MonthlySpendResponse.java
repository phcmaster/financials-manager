package br.com.fm.expensesmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlySpendResponse {

    private Double monthlyValueSpend;

    private Double monthlyAverageSpend;
}
