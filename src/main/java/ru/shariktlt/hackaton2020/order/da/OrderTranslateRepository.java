package ru.shariktlt.hackaton2020.order.da;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;

import java.util.List;
import java.util.UUID;

public interface OrderTranslateRepository extends CrudRepository<OrdersTranslateEntity, UUID> {
    List<OrdersTranslateEntity> findOrdersByClientAndStatus(Long clientId, OrderStatus status, Pageable pageable);

    List<OrdersTranslateEntity> findOrdersByClient(Long clientId, Pageable pageable);

    long countOrdersByClient(Long clientId);

}