package br.com.fm.expensesmanager.mysql.repository;

import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findAllByUserId(String userId);

    Optional<ExpenseEntity> findByIdExpenseAndUserId(Long id, String userId);


}
