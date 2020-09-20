package ru.shariktlt.hackaton2020.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderDto implements Serializable {
    private UUID uuid;
    private OrderStatus status;
}
