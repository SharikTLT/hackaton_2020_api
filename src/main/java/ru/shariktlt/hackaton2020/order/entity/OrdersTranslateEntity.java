package ru.shariktlt.hackaton2020.order.entity;

import lombok.*;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(name = "ORDERS_TRANSLATE")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class OrdersTranslateEntity {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private UserEntity client;

    @Column(name = "LANG_FROM", nullable = false)
    private String langFrom;

    @Column(name = "LANG_TO", nullable = false)
    private String langTo;

    @Column(name = "PAGES", nullable = false)
    private Long pages;

    @Column(name = "FILE_PACKAGE_ORIGINALS")
    private UUID filePackageOriginals;

    @Column(name = "CREATED")
    private GregorianCalendar created;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
