package com.splitly.backend.service;

import com.splitly.backend.entity.*;
import com.splitly.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

    private final GroupMemberRepository groupMemberRepository;
    private final SplitRepository splitRepository;

    public SplitService(
            GroupMemberRepository groupMemberRepository,
            SplitRepository splitRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.splitRepository = splitRepository;
    }

    public void createEqualSplits(Expense expense) {

        // Step 1: Get all members of the group
        List<GroupMember> members =
                groupMemberRepository.findByGroup(expense.getGroup());

        int totalMembers = members.size();

        // Step 2: Calculate equal share
        double equalShare = expense.getAmount() / totalMembers;

        // Step 3: Create split for each member except payer
        for (GroupMember member : members) {

            User memberUser = member.getUser();

            // Skip payer
            if (memberUser.getId().equals(expense.getPaidBy().getId())) {
                continue;
            }

            Split split = new Split();
            split.setExpense(expense);
            split.setUser(memberUser);
            split.setAmountOwed(equalShare);

            splitRepository.save(split);
        }
    }
}
