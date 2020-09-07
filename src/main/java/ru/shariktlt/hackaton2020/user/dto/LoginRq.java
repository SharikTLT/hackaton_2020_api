package ru.shariktlt.hackaton2020.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginRq {
    private String login;
    private String password;
}