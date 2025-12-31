package com.splitly.backend.repository;

import com.splitly.backend.entity.Split;
import com.splitly.backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Long> {

    List<Split> findByExpense(Expense expense);
}
