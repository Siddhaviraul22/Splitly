package com.splitly.backend.service;

import com.splitly.backend.entity.Split;
import com.splitly.backend.repository.SplitRepository;
import org.springframework.stereotype.Service;

@Service
public class SettlementService {

    private final SplitRepository splitRepository;

    public SettlementService(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public void settleSplit(Long splitId) {
        Split split = splitRepository.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

        split.setSettled(true);
        splitRepository.save(split);
    }
}
