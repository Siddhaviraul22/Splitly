package com.splitly.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BalanceResponse {

    private String fromUser;   // who owes
    private String toUser;     // who is owed
    private double amount;
}