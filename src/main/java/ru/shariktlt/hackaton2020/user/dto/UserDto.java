package ru.shariktlt.hackaton2020.user.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.io.Serializable;

@SuperBuilder
@Getter
@Setter
public class UserDto implements Serializable {
    private long id;

    private String name;

    private String email;


    public static UserDto map(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

}
