package ru.shariktlt.hackaton2020.order.da;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface OrderTranslateRepository extends PagingAndSortingRepository<OrdersTranslateEntity, UUID> {
    List<OrdersTranslateEntity> findOrdersByClientAndStatus(Long clientId, OrderStatus status, Pageable pageable);

    List<OrdersTranslateEntity> findAllOrdersByClient(UserEntity user, Pageable pageable);

    long countOrdersByClient(UserEntity user);

}