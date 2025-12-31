package com.splitly.backend.service;

import com.splitly.backend.dto.AddExpenseRequest;
import com.splitly.backend.entity.*;
import com.splitly.backend.repository.*;
import com.splitly.backend.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final SplitService splitService;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            GroupRepository groupRepository,
            UserRepository userRepository,
            SplitService splitService) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.splitService = splitService;
    }

    // ADD EXPENSE
    public Expense addExpense(Long groupId, AddExpenseRequest request) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email);

        Expense expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        expense.setGroup(group);
        expense.setPaidBy(user);

        Expense savedExpense = expenseRepository.save(expense);

        // ⭐ CREATE SPLITS
        splitService.createEqualSplits(savedExpense);

        return savedExpense;
    }

    // GET GROUP EXPENSES
    public List<Expense> getExpenses(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        return expenseRepository.findByGroup(group);
    }
}
