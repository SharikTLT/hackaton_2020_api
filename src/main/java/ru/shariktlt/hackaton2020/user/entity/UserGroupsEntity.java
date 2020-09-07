package ru.shariktlt.hackaton2020.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USER_GROUPS")
@IdClass(UserGroupCompositId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupsEntity {

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @Id
    @Column(name = "GROUP_NAME")
    private String group;
}
