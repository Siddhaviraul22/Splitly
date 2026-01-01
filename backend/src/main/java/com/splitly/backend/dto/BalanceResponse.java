package com.splitly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceResponse {
    private String fromUser;
    private String toUser;
    private double amount;
}
