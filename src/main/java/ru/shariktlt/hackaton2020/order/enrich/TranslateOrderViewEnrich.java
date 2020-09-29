package ru.shariktlt.hackaton2020.order.enrich;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;
import ru.shariktlt.hackaton2020.order.TextProvider;
import ru.shariktlt.hackaton2020.order.TextTypes;
import ru.shariktlt.hackaton2020.order.dto.ClientResultView;
import ru.shariktlt.hackaton2020.order.dto.SingleOrderTranlsate;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.GroupDependable;

import java.util.ArrayList;

@Component
public class TranslateOrderViewEnrich {

    @Autowired
    private TextProvider textProvider;

    private GroupDependable<OrdersTranslateEntity, SingleOrderTranlsate> groupDependable = new GroupDependable<>();

    public TranslateOrderViewEnrich() {
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
        dto.setClientResultView(
                ClientResultView.builder()
                        .title(textProvider.getTextForKey(TextTypes.STATUS_TITLE, entity.getStatus().name()))
                        .desc(textProvider.getTextForKey(TextTypes.STATUS_DESCRIPTION, entity.getStatus().name()))
                        .build()
        );
        return dto;
    }
}
