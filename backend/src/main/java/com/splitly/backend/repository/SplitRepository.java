package com.splitly.backend.repository;

import com.splitly.backend.entity.Split;
import com.splitly.backend.entity.Expense;
import com.splitly.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Long> {

    List<Split> findByExpense(Expense expense);
    List<Split> findByExpense_Group_Id(Long groupId);
    List<Split> findByUser_IdAndSettledFalse(Long userId);
    List<Split> findByExpense_PaidBy_IdAndSettledFalse(Long userId);
    List<Split> findByUserAndSettledFalse(User user);

}