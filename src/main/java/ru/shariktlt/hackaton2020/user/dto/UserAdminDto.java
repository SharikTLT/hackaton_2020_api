package ru.shariktlt.hackaton2020.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
public class UserAdminDto implements Serializable {

    private UserDto info;
    private Set<String> groups;
}
