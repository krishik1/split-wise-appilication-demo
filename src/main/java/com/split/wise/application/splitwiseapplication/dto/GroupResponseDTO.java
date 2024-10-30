package com.split.wise.application.splitwiseapplication.dto;

import com.split.wise.application.splitwiseapplication.model.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseDTO {
    private String name;
    private User createdBy;
    private List<User> members;
}
