package ru.shariktlt.hackaton2020.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shariktlt.hackaton2020.core.dto.ApiResponse;
import ru.shariktlt.hackaton2020.core.service.AuthorizationService;
import ru.shariktlt.hackaton2020.user.dto.UserAdminDto;
import ru.shariktlt.hackaton2020.user.dto.UserDto;
import ru.shariktlt.hackaton2020.user.dto.UserListRq;
import ru.shariktlt.hackaton2020.user.dto.UserListRs;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.success;
import static ru.shariktlt.hackaton2020.core.enums.GroupsEnum.ADMIN;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user/admin")
public class UserAdminEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService auth;

    @RequestMapping("/list")
    public ApiResponse<? extends Serializable> register(@RequestBody UserListRq rq) {
        auth.checkAccess(ADMIN);
        List<UserDto> items = userService.getList(rq.getOffset(), rq.getMax()).stream()
                .map(UserDto::map)
                .collect(Collectors.toList());
        long total = userService.getTotal();
        return success(
                UserListRs.builder()
                        .offset(rq.getOffset())
                        .total(total)
                        .items(items)
                        .build()
        );

    }

    @RequestMapping("/user/{id}")
    public ApiResponse<? extends Serializable> getUser(@PathVariable("id") Long id) {
        auth.checkAccess(ADMIN);
        return success(UserAdminDto.builder()
                .info(UserDto.map(userService.getUser(id)))
                .groups(userService.getUserGroups(id))
                .build()
        );
    }


}
