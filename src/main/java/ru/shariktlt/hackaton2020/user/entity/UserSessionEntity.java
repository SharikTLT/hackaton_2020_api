package ru.shariktlt.hackaton2020.user.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(name = "USER_SESSION")
@Getter
@Setter
@EqualsAndHashCode
public class UserSessionEntity {

    @Id
    @Column(name = "UUID", unique = true, nullable = false)
    @GeneratedValue(generator = "GEN_UUID_USER_SESSION")
    @GenericGenerator(
            name = "GEN_UUID_USER_SESSION",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "dead", nullable = false)
    private boolean dead;

    @Column(name = "last_update", nullable = false)
    private GregorianCalendar last_update;
}
