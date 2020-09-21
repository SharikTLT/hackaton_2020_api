package ru.shariktlt.hackaton2020.order.dto;

import lombok.*;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleOrderTranlsate implements Serializable {
    private UUID uuid;

    private OrderStatus status;

    private Date created;

    private String langFrom;

    private String langTo;

    private Long pages;

    private UUID filePackageOriginals;


    public static SingleOrderTranlsate fromEntity(OrdersTranslateEntity entity) {
        return builder()
                .uuid(entity.getId())
                .created(entity.getCreated().getTime())
                .langFrom(entity.getLangFrom())
                .langTo(entity.getLangTo())
                .filePackageOriginals(entity.getFilePackageOriginals())
                .pages(entity.getPages())
                .status(entity.getStatus())
                .build();
    }
}
