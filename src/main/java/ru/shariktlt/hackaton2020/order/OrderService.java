package ru.shariktlt.hackaton2020.order;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;
import ru.shariktlt.hackaton2020.order.da.OrderTranslateRepository;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.util.List;
import java.util.UUID;

@Component
public class OrderService {
    @Autowired
    private OrderTranslateRepository orderTranslateRepository;

    public OrdersTranslateEntity createOrder(UserEntity client, OrdersTranslateEntity order) {
        order.setClient(client);
        //createPayment
        order.setStatus(OrderStatus.PAYED);
        orderTranslateRepository.save(order);
        return order;
    }

    public List<OrdersTranslateEntity> getOrderList(UserEntity user, int page, int limit) {
        return orderTranslateRepository.findOrdersByClient(user.getId(), PageRequest.of(page, limit));
    }

    public long getOrderListTotal(UserEntity user) {
        return orderTranslateRepository.countOrdersByClient(user.getId());
    }

    @SneakyThrows
    public OrdersTranslateEntity getOrder(UUID id, ClientApiContext clientCtx) {
        OrdersTranslateEntity order = orderTranslateRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (ifViewerNotOwner(clientCtx, order)) {
            throw new IllegalAccessException();
        }

        return order;
    }

    private boolean ifViewerNotOwner(ClientApiContext clientCtx, OrdersTranslateEntity order) {
        return clientCtx.inGroup(GroupsEnum.USER.getName()) && !order.getClient().getId().equals(clientCtx.getUserEntity().getId());
    }
}
