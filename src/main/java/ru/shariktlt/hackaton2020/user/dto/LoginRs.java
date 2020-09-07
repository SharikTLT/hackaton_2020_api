package ru.shariktlt.hackaton2020.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class LoginRs implements Serializable {

    private String token;

}