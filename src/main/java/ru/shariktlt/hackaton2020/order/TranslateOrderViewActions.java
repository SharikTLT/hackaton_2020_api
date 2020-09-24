package ru.shariktlt.hackaton2020.order;

import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;
import ru.shariktlt.hackaton2020.order.dto.SingleOrderTranlsate;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.GroupDependable;

import java.util.ArrayList;

@Component
public class TranslateOrderViewActions {

    private GroupDependable<OrdersTranslateEntity, SingleOrderTranlsate> groupDependable = new GroupDependable<>();

    public TranslateOrderViewActions() {
        groupDependable.add(GroupsEnum.USER, this::enrichForUser);
        groupDependable.add(GroupsEnum.TRANSLATOR, this::enrichForTranslator);
        groupDependable.add(GroupsEnum.NOTARIUS, this::enrichForNotarius);
        groupDependable.add(GroupsEnum.ADMIN, this::enrichForAdmin);
    }

    public SingleOrderTranlsate enrichForActions(OrdersTranslateEntity order, ClientApiContext ctx) {
        SingleOrderTranlsate dto = SingleOrderTranlsate.fromEntity(order);
        dto.setActions(new ArrayList<>());
        return groupDependable.process(order, dto, ctx);
    }

    private SingleOrderTranlsate enrichForAdmin(OrdersTranslateEntity entity, SingleOrderTranlsate dto, ClientApiContext ctx) {
        dto.getActions().add("*");
        return dto;
    }

    private SingleOrderTranlsate enrichForTranslator(OrdersTranslateEntity entity, SingleOrderTranlsate dto, ClientApiContext ctx) {
        dto.getActions().add("viewNotariusTranslator");
        return dto;
    }


    private SingleOrderTranlsate enrichForNotarius(OrdersTranslateEntity entity, SingleOrderTranlsate dto, ClientApiContext ctx) {
        dto.getActions().add("viewNotariusTools");
        return dto;
    }

    private SingleOrderTranlsate enrichForUser(OrdersTranslateEntity entity, SingleOrderTranlsate dto, ClientApiContext ctx) {
        dto.getActions().add("viewClientResult");
        return dto;
    }
}
