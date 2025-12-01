package com.splitly.backend.repository;

import com.splitly.backend.entity.Split;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SplitRepository extends JpaRepository<Split, Long> {}
