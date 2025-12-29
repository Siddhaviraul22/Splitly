package com.splitly.backend.service;

import com.splitly.backend.entity.*;
import com.splitly.backend.repository.*;
import com.splitly.backend.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupMemberService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMemberRepository groupMemberRepository;

    public GroupMemberService(
            GroupRepository groupRepository,
            UserRepository userRepository,
            GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    // ADD MEMBER
    public GroupMember addMember(Long groupId, Long userId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        String currentEmail = SecurityUtil.getCurrentUserEmail();
        if (!group.getCreatedBy().getEmail().equals(currentEmail)) {
            throw new RuntimeException("Only group creator can add members");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        groupMemberRepository.findByGroupAndUser(group, user)
                .ifPresent(m -> {
                    throw new RuntimeException("User already in group");
                });

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);

        return groupMemberRepository.save(member);
    }

    // LIST MEMBERS
    public List<GroupMember> getMembers(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        return groupMemberRepository.findByGroup(group);
    }

    // REMOVE MEMBER
    public void removeMember(Long groupId, Long userId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        String currentEmail = SecurityUtil.getCurrentUserEmail();
        if (!group.getCreatedBy().getEmail().equals(currentEmail)) {
            throw new RuntimeException("Only group creator can remove members");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroupMember member = groupMemberRepository
                .findByGroupAndUser(group, user)
                .orElseThrow(() -> new RuntimeException("User not in group"));

        groupMemberRepository.delete(member);
    }
}
