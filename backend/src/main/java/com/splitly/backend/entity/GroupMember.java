package com.splitly.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_member")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Which group
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    // Member name (basic version, not registered user)
    private String memberName;

    // getters and setters
    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
