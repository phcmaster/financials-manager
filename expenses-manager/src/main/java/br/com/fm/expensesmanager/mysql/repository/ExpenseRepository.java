package br.com.fm.expensesmanager.mysql.repository;

import br.com.fm.expensesmanager.mysql.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findAllByUserId(String userId);

    Optional<ExpenseEntity> findByIdExpenseAndUserId(Long id, String userId);

    @Transactional
    void deleteByIdExpenseAndUserId(Long id, String userId);

    @Query(value = "select sum(value) from expense_entity where MONTH(due_date)=MONTH(CURDATE()) and user_id=:userId", nativeQuery = true)
    Double monthlyValueSpend(@Param("userId") String userId);

    @Query(value = "select avg(value) from expense_entity where MONTH(due_date)=MONTH(CURDATE()) and user_id=:userId", nativeQuery = true)
    Double monthlyAverageSpend(@Param("userId") String userId);

}
