package ru.shariktlt.hackaton2020.user.dto;

import lombok.Getter;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.shariktlt.hackaton2020.core.enums.GroupsEnum.ADMIN;
import static ru.shariktlt.hackaton2020.core.enums.GroupsEnum.ANY;

public enum Modules {
    USER_EDITOR(ADMIN),
    CREATE_ORDER(ANY);

    @Getter
    private Set<GroupsEnum> groupsEnums;

    @Getter
    private String id;

    Modules(GroupsEnum... groupsEnums) {
        this(null, groupsEnums);
        this.id = this.name();
    }

    Modules(String id, GroupsEnum... groupsEnum) {
        groupsEnums = new HashSet<>();
        groupsEnums.addAll(Arrays.asList(groupsEnum));
        this.id = id;
    }


}
