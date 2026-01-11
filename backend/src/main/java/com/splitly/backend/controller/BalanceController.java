package com.splitly.backend.controller;

import com.splitly.backend.dto.BalanceResponse;
import com.splitly.backend.service.BalanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/group/{groupId}")
    public List<BalanceResponse> getBalances(@PathVariable Long groupId) {
        return balanceService.getGroupBalances(groupId);
    }
}