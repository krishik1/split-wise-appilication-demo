package com.split.wise.application.splitwiseapplication.mapper;

import com.split.wise.application.splitwiseapplication.dto.GroupResponseDTO;
import com.split.wise.application.splitwiseapplication.dto.UserFriendResponseDTO;
import com.split.wise.application.splitwiseapplication.dto.UserLoginResponseDTO;
import com.split.wise.application.splitwiseapplication.model.Group;
import com.split.wise.application.splitwiseapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {

    public static UserLoginResponseDTO toDTO(User user) {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        userLoginResponseDTO.setName(user.getName());
        userLoginResponseDTO.setEmail(user.getEmail());
        userLoginResponseDTO.setId(user.getId());
        List<UserFriendResponseDTO> friendsList = new ArrayList<>();
        if(!user.getFriends().equals(null)) {
            for(User friend : user.getFriends()) {
                friendsList.add(toFriend(friend));
            }
        }
        List<GroupResponseDTO> groups = new ArrayList<>();
        if(!user.getGroups().equals(null)) {
            for (Group group : user.getGroups()) {
                groups.add(toGroup(group));
            }
        }
        userLoginResponseDTO.setFriends(friendsList);
        userLoginResponseDTO.setGroups(groups);
        return userLoginResponseDTO;
    }

    private static GroupResponseDTO toGroup(Group group) {
        GroupResponseDTO responseDTO = new GroupResponseDTO();
        responseDTO.setName(group.getName());
        responseDTO.setCreatedBy(group.getCreatedBy());
        List<User> users = new ArrayList<>();

        for(User user : group.getMembers()) {
            users.add(user);
        }
        responseDTO.setMembers(users);
        return responseDTO;
    }

    private static UserFriendResponseDTO toFriend(User friend) {
        UserFriendResponseDTO responseDTO = new UserFriendResponseDTO();
        responseDTO.setId(friend.getId());
        responseDTO.setName(friend.getName());
        return responseDTO;
    }
}
