package com.splitly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardResponse {

    private double totalSpent;
    private double youOwe;
    private double youAreOwed;
}
