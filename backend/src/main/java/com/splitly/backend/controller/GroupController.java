package com.splitly.backend.controller;

import com.splitly.backend.dto.CreateGroupRequest;
import com.splitly.backend.entity.Group;
import com.splitly.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group createGroup(@RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request);
    }
}
