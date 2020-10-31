package br.com.fm.expensesmanager.mapper;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.dto.ExpenseResponse;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ExpenseRegisterMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "dueDate", source = "dueDate")
    ExpenseEntity mapToEntity(ExpenseRequest request, String userId, LocalDate dueDate);


    List<ExpenseResponse> mapToResponse(List<ExpenseEntity> entities);

}
