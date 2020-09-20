package ru.shariktlt.hackaton2020.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shariktlt.hackaton2020.core.dto.ApiResponse;
import ru.shariktlt.hackaton2020.core.service.AuthorizationService;
import ru.shariktlt.hackaton2020.order.dto.CreateOrderDto;
import ru.shariktlt.hackaton2020.order.dto.OrderStatus;

import java.io.Serializable;
import java.util.UUID;

import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.error;
import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.success;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderEndpoint {

    @Autowired
    private AuthorizationService auth;


    @PostMapping("/create")
    public ApiResponse<? extends Serializable> upload(@RequestParam MultipartFile[] files) {
        auth.getCtx();
        try {
            CreateOrderDto rs = new CreateOrderDto();
            rs.setUuid(UUID.randomUUID());
            rs.setStatus(OrderStatus.CREATED);
            return success(rs);
        } catch (DuplicateKeyException e) {
            return error("Пользователь с таким email уже существует");
        } catch (Exception e) {
            return error("Ошибка загрузки");
        }
    }
}
