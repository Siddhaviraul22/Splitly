package com.splitly.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddExpenseRequest {

    private String description;
    private Double amount;
    private String category;
    private LocalDate date;
}
