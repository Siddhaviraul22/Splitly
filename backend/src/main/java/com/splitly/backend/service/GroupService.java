package com.splitly.backend.service;

import com.splitly.backend.dto.CreateGroupRequest;
import com.splitly.backend.entity.Group;
import com.splitly.backend.entity.User;
import com.splitly.backend.repository.GroupRepository;
import com.splitly.backend.repository.UserRepository;
import com.splitly.backend.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public Group createGroup(CreateGroupRequest request) {

        // 1️⃣ Get logged-in user email
        String email = SecurityUtil.getCurrentUserEmail();

        // 2️⃣ Fetch user from database
        User user = userRepository.findByEmail(email);

        // 3️⃣ Create group
        Group group = new Group();
        group.setGroupName(request.groupName);
        group.setCreatedBy(user);

        // 4️⃣ Save group
        return groupRepository.save(group);
    }
}
