package ru.shariktlt.hackaton2020.user.dto;

import lombok.Getter;
import lombok.Setter;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

@Getter
@Setter
public class RegisterRq {

    private String name;

    private String email;

    private String password;

    public UserEntity toUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setEmail(email);
        entity.setPassword(password);
        return entity;
    }
}
