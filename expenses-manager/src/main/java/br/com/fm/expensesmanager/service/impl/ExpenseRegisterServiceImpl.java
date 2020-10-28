package br.com.fm.expensesmanager.service.impl;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.service.ExpenseRegisterService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseRegisterServiceImpl implements ExpenseRegisterService {


    @Override
    public String registerExpense(ExpenseRequest request) {
        return "Hello, world!";

    }
}
