package com.split.wise.application.splitwiseapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLoginResponseDTO {
    private int id;
    private String name;
    private String email;
    private List<UserFriendResponseDTO> friends;
    private List<GroupResponseDTO> groups;
}
