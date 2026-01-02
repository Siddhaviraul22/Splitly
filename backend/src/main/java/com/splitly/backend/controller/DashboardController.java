package com.splitly.backend.controller;

import com.splitly.backend.dto.DashboardSummary;
import com.splitly.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardSummary getDashboard() {
        return dashboardService.getDashboard();
    }
}