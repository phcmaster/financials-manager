package br.com.fm.expensesmanager.mysql.repository;

import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
