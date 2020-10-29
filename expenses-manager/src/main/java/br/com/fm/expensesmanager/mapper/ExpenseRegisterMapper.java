package br.com.fm.expensesmanager.mapper;


import br.com.fm.expensesmanager.dto.ExpenseRequest;
import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ExpenseRegisterMapper {

    ExpenseEntity mapToEntity(ExpenseRequest request, String userId);

}
