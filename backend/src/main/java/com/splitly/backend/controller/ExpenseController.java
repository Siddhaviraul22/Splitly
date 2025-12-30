package com.splitly.backend.controller;

import com.splitly.backend.dto.AddExpenseRequest;
import com.splitly.backend.entity.Expense;
import com.splitly.backend.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense addExpense(
            @PathVariable Long groupId,
            @RequestBody AddExpenseRequest request) {

        return expenseService.addExpense(groupId, request);
    }

    @GetMapping
    public List<Expense> getExpenses(@PathVariable Long groupId) {
        return expenseService.getExpenses(groupId);
    }
}
