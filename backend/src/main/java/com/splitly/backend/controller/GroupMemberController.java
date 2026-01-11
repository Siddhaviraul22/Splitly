package com.splitly.backend.controller;

import com.splitly.backend.dto.AddMemberRequest;
import com.splitly.backend.entity.GroupMember;
import com.splitly.backend.service.GroupMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/members")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    public GroupMember addMember(
            @PathVariable Long groupId,
            @RequestBody AddMemberRequest request) {

        return groupMemberService.addMember(groupId, request.getUserId());
    }

    @GetMapping
    public List<GroupMember> getMembers(@PathVariable Long groupId) {
        return groupMemberService.getMembers(groupId);
    }

    @DeleteMapping("/{userId}")
    public void removeMember(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        groupMemberService.removeMember(groupId, userId);
    }
}