package ru.shariktlt.hackaton2020.user.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_USER"
    )
    @SequenceGenerator(
            name = "SEQ_USER",
            allocationSize = 1
    )
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NAME", length = 128, nullable = false)
    private String name;

    @Column(name = "PASSWORD_HASH", length = 128, nullable = false)
    private String passwordHash;

    @Column(name = "EMAIL", length = 256, nullable = false)
    private String email;

    @Transient
    private String password;
}
