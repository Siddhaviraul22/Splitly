package com.splitly.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    // getters and setters
}