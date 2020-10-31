package br.com.fm.expensesmanager.service;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.ExpenseResponse;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {

    void registerExpense(ExpenseRequest request) throws ParseException;

    void updateExpense(Long id, ExpenseRequest request);

    List<ExpenseResponse>listAllExpenses();



}
