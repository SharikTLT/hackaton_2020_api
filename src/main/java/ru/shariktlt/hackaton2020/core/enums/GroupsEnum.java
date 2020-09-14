package ru.shariktlt.hackaton2020.core.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum GroupsEnum {
    ANY("*"),
    ADMIN("admin"),
    USER("user");

    private String name;

    GroupsEnum(String name) {
        this.name = name;
    }

    public static GroupsEnum getByName(String name) {
        return Arrays.stream(GroupsEnum.values()).filter(group -> group.name.equals(name)).findFirst().orElse(null);
    }
}
