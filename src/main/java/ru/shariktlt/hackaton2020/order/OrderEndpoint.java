package ru.shariktlt.hackaton2020.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import ru.shariktlt.hackaton2020.core.dto.ApiResponse;
import ru.shariktlt.hackaton2020.core.dto.PaginatedRequest;
import ru.shariktlt.hackaton2020.core.dto.PaginatedResponse;
import ru.shariktlt.hackaton2020.core.service.AuthorizationService;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;
import ru.shariktlt.hackaton2020.order.dto.CreateOrderDtoRq;
import ru.shariktlt.hackaton2020.order.dto.MultipleOrderTranslate;
import ru.shariktlt.hackaton2020.order.dto.SingleOrderTranlsate;
import ru.shariktlt.hackaton2020.order.entity.OrdersTranslateEntity;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.error;
import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.success;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderEndpoint {

    @Autowired
    private AuthorizationService auth;

    @Autowired
    private OrderService orderService;

    @Autowired
    private TranslateOrderViewActions translateOrderViewActions;

    @PostMapping("/create")
    public ApiResponse<? extends Serializable> create(@RequestBody CreateOrderDtoRq createOrderDto) {
        ClientApiContext ctx = auth.getCtx();
        try {
            OrdersTranslateEntity order = orderService.createOrder(ctx.getUserEntity(), createOrderDto.getOrderEntity());
            SingleOrderTranlsate rs = SingleOrderTranlsate.fromEntity(order);
            return success(rs);
        } catch (DuplicateKeyException e) {
            return error("Заказ с таким uuid уже существует");
        } catch (Exception e) {
            return error("Ошибка создания заказа");
        }
    }

    @PostMapping("/getMyOrders")
    public ApiResponse<? extends Serializable> getUserOrders(@RequestBody PaginatedRequest rq) {
        ClientApiContext ctx = auth.getCtx();
        try {
            UserEntity user = ctx.getUserEntity();
            List<OrdersTranslateEntity> list = orderService.getOrderList(user, rq.getOffset(), rq.getMax());
            long total = orderService.getOrderListTotal(user);
            PaginatedResponse<SingleOrderTranlsate> rs = MultipleOrderTranslate.builder()
                    .total(total)
                    .items(
                            list.stream()
                                    .map(SingleOrderTranlsate::fromEntity)
                                    .collect(Collectors.toList())
                    )
                    .build();
            return success(rs);
        } catch (DuplicateKeyException e) {
            return error("Заказ с таким uuid уже существует");
        } catch (Exception e) {
            return error("Ошибка создания заказа");
        }
    }

    @PostMapping("/view/{id}")
    public ApiResponse<? extends Serializable> view(@PathVariable("id") UUID id) {
        ClientApiContext ctx = auth.getCtx();
        try {
            UserEntity user = ctx.getUserEntity();

            OrdersTranslateEntity order = orderService.getOrder(id, ctx);

            return success(translateOrderViewActions.enrichForActions(order, ctx));
        } catch (DuplicateKeyException e) {
            return error("Заказ с таким uuid уже существует");
        } catch (Exception e) {
            return error("Ошибка создания заказа");
        }
    }
}
