package br.com.fm.expensesmanager.service.impl;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.mapper.ExpenseRegisterMapper;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import br.com.fm.expensesmanager.mysql.repository.ExpenseRepository;
import br.com.fm.expensesmanager.service.ExpenseService;
import br.com.fm.expensesmanager.utils.JwtUtils;
import lombok.SneakyThrows;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dueDate = LocalDate.parse(request.getDueDate() ,formatter);

        String userId = jwtUtils.getSession().getId();
        ExpenseEntity expenseEntity = mapper.mapToEntity(request, userId, dueDate);

        repository.save(expenseEntity);
    }

    @Override
    public void updateExpense(ExpenseRequest request) {

    }

    @Override
    public List<ExpenseEntity> listAllExpenses() {
        return repository.findAll();
    }


}
