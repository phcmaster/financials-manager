package br.com.fm.expensesmanager.service;


import br.com.fm.expensesmanager.dto.ExpenseRequest;

public interface ExpenseRegisterService {

    String registerExpense(ExpenseRequest request);

}
