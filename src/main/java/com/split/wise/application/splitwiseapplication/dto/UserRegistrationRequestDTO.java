package com.split.wise.application.splitwiseapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDTO {
    private String name;
    private String password;
    private String email;
}
