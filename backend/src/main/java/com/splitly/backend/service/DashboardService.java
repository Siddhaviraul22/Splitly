package com.splitly.backend.service;

import com.splitly.backend.dto.DashboardResponse;
import com.splitly.backend.entity.Expense;
import com.splitly.backend.entity.Split;
import com.splitly.backend.entity.User;
import com.splitly.backend.repository.ExpenseRepository;
import com.splitly.backend.repository.SplitRepository;
import com.splitly.backend.repository.UserRepository;
import com.splitly.backend.security.SecurityUtil;
import org.springframework.stereotype.Service;


@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final SplitRepository splitRepository;

    public DashboardService(UserRepository userRepository,
                            ExpenseRepository expenseRepository,
                            SplitRepository splitRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.splitRepository = splitRepository;
    }

    public DashboardResponse getDashboard() {

        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email);

        double totalSpent = expenseRepository.findByPaidBy(user)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double youOwe = splitRepository.findByUserAndSettledFalse(user)
                .stream()
                .mapToDouble(Split::getAmountOwed)
                .sum();

        double youAreOwed = splitRepository.findAll().stream()
                .filter(s -> s.getExpense().getPaidBy().equals(user))
                .filter(s -> !s.isSettled())
                .mapToDouble(Split::getAmountOwed)
                .sum();

        return new DashboardResponse(totalSpent, youOwe, youAreOwed);
    }
}
