package com.split.wise.application.splitwiseapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name="SPLIWISE_USER")
public class User extends BaseModel{
    private String name;
    private String password;
    private String email;
    @ManyToMany
    private List<User> friends;
    @ManyToMany
    private List<Group> groups;
}
