package br.com.fm.expensesmanager.service.impl;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.ExpenseResponse;
import br.com.fm.expensesmanager.dto.MonthlySpendResponse;
import br.com.fm.expensesmanager.mapper.ExpenseRegisterMapper;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import br.com.fm.expensesmanager.mysql.repository.ExpenseRepository;
import br.com.fm.expensesmanager.service.ExpenseService;
import br.com.fm.expensesmanager.utils.JwtUtils;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRegisterMapper mapper = Mappers.getMapper(ExpenseRegisterMapper.class);

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @SneakyThrows
    public void registerExpense(ExpenseRequest request){

        String userId = jwtUtils.getSession().getId();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dueDate = LocalDate.parse(request.getDueDate(), formatter);

        ExpenseEntity expenseEntity = mapper.mapToEntity(request, userId, dueDate);
        repository.save(expenseEntity);
    }

    @Override
    public void updateExpense(Long id, ExpenseRequest request) {
        String userId = jwtUtils.getSession().getId();

        Optional<ExpenseEntity> expense = repository.findByIdExpenseAndUserId(id, userId);

        if(expense.isPresent()){
            expense.get().setDescription(request.getDescription());
            expense.get().setDueDate(LocalDate.parse(request.getDueDate()));
            expense.get().setExpenseName(request.getExpenseName());
            expense.get().setInstallment(request.isInstallment());
            expense.get().setInstallmentTimes(request.getInstallmentTimes());
            expense.get().setValue(request.getValue());

            repository.save(expense.get());
        }

    }


    @Override
    public List<ExpenseResponse> listAllExpenses() {
        String userId = jwtUtils.getSession().getId();

        List<ExpenseEntity> expenses = repository.findAllByUserId(userId);
        List<ExpenseResponse> expenseResponse = mapper.mapToResponse(expenses);
        return  expenseResponse;
    }


    @Override
    public void deleteExpense(Long id) {
        String userId = jwtUtils.getSession().getId();
        repository.deleteByIdExpenseAndUserId(id, userId);
    }

    @Override
    @SneakyThrows
    public ExpenseResponse listById(Long id) {
        String userId = jwtUtils.getSession().getId();
        ExpenseEntity expense = repository.findByIdExpenseAndUserId(id, userId).orElseThrow(() ->  new NotFoundException("Despesa nao encontrada."));
        ExpenseResponse expenseResponse = mapper.entityToResponse(expense);

        return expenseResponse;
    }

    @Override
    public Double monthlyValueSpend() {
        String userId = jwtUtils.getSession().getId();
        return repository.monthlyValueSpend(userId);
    }

    @Override
    public Double monthlyAverageSpend() {
        String userId = jwtUtils.getSession().getId();
        return repository.monthlyAverageSpend(userId);
    }


    public MonthlySpendResponse monthlySpend(){
        MonthlySpendResponse monthlySpendResponse = new MonthlySpendResponse();

        Double valueSpend = this.monthlyValueSpend();
        Double avgSpend = this.monthlyAverageSpend();

        if(valueSpend == null && avgSpend == null){
            monthlySpendResponse.setMonthlyValueSpend(0.00);
            monthlySpendResponse.setMonthlyAverageSpend(0.00);
        }else if(valueSpend == null){
            monthlySpendResponse.setMonthlyValueSpend(0.00);
            monthlySpendResponse.setMonthlyAverageSpend(avgSpend);
        }else if (avgSpend == null){
            monthlySpendResponse.setMonthlyAverageSpend(0.00);
            monthlySpendResponse.setMonthlyValueSpend(valueSpend);
        }else {
            monthlySpendResponse.setMonthlyAverageSpend(avgSpend);
            monthlySpendResponse.setMonthlyValueSpend(valueSpend);
        }
        return monthlySpendResponse;

    }


}
