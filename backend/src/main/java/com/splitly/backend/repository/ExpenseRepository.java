package com.splitly.backend.repository;

import com.splitly.backend.entity.Expense;
import com.splitly.backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByGroup(Group group);
    List<Expense> findByGroupId(Long groupId);
    List<Expense> findByPaidBy_Id(Long userId);
}