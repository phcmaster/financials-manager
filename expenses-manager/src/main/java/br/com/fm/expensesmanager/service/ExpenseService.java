package br.com.fm.expensesmanager.service;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;

import java.util.List;

public interface ExpenseService {

    void registerExpense(ExpenseRequest request);

    void updateExpense(ExpenseRequest request);

    List<ExpenseEntity> listAllExpenses();



}
