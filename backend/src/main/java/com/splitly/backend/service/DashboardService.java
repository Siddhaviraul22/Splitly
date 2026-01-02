package com.splitly.backend.service;

import com.splitly.backend.dto.DashboardSummary;
import com.splitly.backend.entity.Expense;
import com.splitly.backend.entity.Split;
import com.splitly.backend.entity.User;
import com.splitly.backend.repository.ExpenseRepository;
import com.splitly.backend.repository.SplitRepository;
import com.splitly.backend.repository.UserRepository;
import com.splitly.backend.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final ExpenseRepository expenseRepository;
    private final SplitRepository splitRepository;
    private final UserRepository userRepository;

    public DashboardService(
            ExpenseRepository expenseRepository,
            SplitRepository splitRepository,
            UserRepository userRepository) {

        this.expenseRepository = expenseRepository;
        this.splitRepository = splitRepository;
        this.userRepository = userRepository;
    }

    public DashboardSummary getDashboard() {

        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email);

        // TOTAL SPENT
        List<Expense> expensesPaid = expenseRepository.findByPaidBy_Id(user.getId());
        double totalSpent = expensesPaid.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        // YOU OWE
        List<Split> youOweSplits =
                splitRepository.findByUser_IdAndSettledFalse(user.getId());

        double youOwe = youOweSplits.stream()
                .mapToDouble(Split::getAmountOwed)
                .sum();

        // YOU ARE OWED
        List<Split> youAreOwedSplits =
                splitRepository.findByExpense_PaidBy_IdAndSettledFalse(user.getId());

        double youAreOwed = youAreOwedSplits.stream()
                .mapToDouble(Split::getAmountOwed)
                .sum();

        return new DashboardSummary(totalSpent, youOwe, youAreOwed);
    }
}
