package ru.shariktlt.hackaton2020.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.order.da.OrderTranslateRepository;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.util.List;

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
}
