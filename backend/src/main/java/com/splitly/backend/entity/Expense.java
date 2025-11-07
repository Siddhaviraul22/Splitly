package com.splitly.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Group group;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    private String category;
    private LocalDate date;
}
