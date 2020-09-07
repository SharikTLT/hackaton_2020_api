package ru.shariktlt.hackaton2020.user.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupCompositId implements Serializable {
    private Long userId;
    private String group;
}
