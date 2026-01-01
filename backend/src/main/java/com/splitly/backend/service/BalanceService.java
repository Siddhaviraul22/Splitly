package com.splitly.backend.service;

import com.splitly.backend.dto.BalanceResponse;
import com.splitly.backend.entity.Split;
import com.splitly.backend.repository.SplitRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BalanceService {

    private final SplitRepository splitRepository;

    public BalanceService(SplitRepository splitRepository) {
        this.splitRepository = splitRepository;
    }

    public List<BalanceResponse> getGroupBalances(Long groupId) {

        List<Split> splits = splitRepository.findByExpense_Group_Id(groupId);

        Map<String, Map<String, Double>> balanceMap = new HashMap<>();

        for (Split split : splits) {

            if (split.getAmountOwed() == 0) {
                continue; // payer
            }

            String from = split.getUser().getName();
            String to = split.getExpense().getPaidBy().getName();
            double amount = split.getAmountOwed();

            balanceMap
                    .computeIfAbsent(from, k -> new HashMap<>())
                    .merge(to, amount, Double::sum);
        }

        List<BalanceResponse> result = new ArrayList<>();

        for (String from : balanceMap.keySet()) {
            for (String to : balanceMap.get(from).keySet()) {
                result.add(
                        new BalanceResponse(from, to, balanceMap.get(from).get(to))
                );
            }
        }

        return result;
    }
}
