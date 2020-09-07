package ru.shariktlt.hackaton2020.core.enums;

import lombok.Getter;

@Getter
public enum GroupsEnum {
    ADMIN("admin");

    private String name;

    GroupsEnum(String name) {
        this.name = name;
    }
}
