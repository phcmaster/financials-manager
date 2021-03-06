package br.com.fm.expensesmanager.service;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.ExpenseResponse;
import br.com.fm.expensesmanager.dto.MonthlySpendResponse;
import javassist.NotFoundException;

import java.text.ParseException;
import java.util.List;

public interface ExpenseService {

    void registerExpense(ExpenseRequest request) throws ParseException;

    void updateExpense(Long id, ExpenseRequest request);

    void deleteExpense(Long id);

   ExpenseResponse listById(Long id) throws NotFoundException;

    List<ExpenseResponse>listAllExpenses();

    Double monthlyValueSpend();

    Double monthlyAverageSpend();

    MonthlySpendResponse monthlySpend();



}
