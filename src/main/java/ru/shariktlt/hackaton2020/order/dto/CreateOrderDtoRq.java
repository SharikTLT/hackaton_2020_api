package ru.shariktlt.hackaton2020.order.dto;

import lombok.Getter;
import lombok.Setter;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderDtoRq implements Serializable {

    private String langFrom;

    private String langTo;

    private Long pages;

    private UUID filePackageOriginals;

    public OrdersTranslateEntity getOrderEntity() {
        return OrdersTranslateEntity.builder()
                .id(UUID.randomUUID())
                .created(new GregorianCalendar())
                .langFrom(langFrom)
                .langTo(langTo)
                .pages(pages)
                .filePackageOriginals(filePackageOriginals)
                .status(OrderStatus.CREATED)
                .build();
    }
}
