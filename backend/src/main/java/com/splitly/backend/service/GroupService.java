package com.splitly.backend.service;

import com.splitly.backend.dto.CreateGroupRequest;
import com.splitly.backend.entity.Group;
import com.splitly.backend.entity.GroupMember;
import com.splitly.backend.entity.User;
import com.splitly.backend.repository.GroupMemberRepository;
import com.splitly.backend.repository.GroupRepository;
import com.splitly.backend.repository.UserRepository;
import com.splitly.backend.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository; // ⭐ MISSING LINE

    public Group createGroup(CreateGroupRequest request) {

        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email);

        Group group = new Group();
        group.setGroupName(request.getGroupName());
        group.setCreatedBy(user);

        Group savedGroup = groupRepository.save(group);

        // Add creator as member
        GroupMember creatorMember = new GroupMember();
        creatorMember.setGroup(savedGroup);
        creatorMember.setUser(user);

        groupMemberRepository.save(creatorMember);

        return savedGroup;
    }
}