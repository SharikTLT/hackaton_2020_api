package ru.shariktlt.hackaton2020.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shariktlt.hackaton2020.core.dto.ApiResponse;
import ru.shariktlt.hackaton2020.core.service.AuthorizationService;
import ru.shariktlt.hackaton2020.user.dto.LoginRq;
import ru.shariktlt.hackaton2020.user.dto.LoginRs;
import ru.shariktlt.hackaton2020.user.dto.ModulesRs;
import ru.shariktlt.hackaton2020.user.dto.RegisterRq;
import ru.shariktlt.hackaton2020.user.entity.UserSessionEntity;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationService auth;

    @Autowired
    private ModulesService modulesService;

    @RequestMapping("/register")
    public ApiResponse<? extends Serializable> register(@RequestBody RegisterRq rq) {
        try {
            userService.register(rq.toUserEntity());
            return pushUrl("/login");
        } catch (DuplicateKeyException e) {
            return error("Пользователь с таким email уже существует");
        } catch (Exception e) {
            LOGGER.error("Registration error: {}", e.getMessage());
            return error("Ошибка регистрации");
        }
    }

    @RequestMapping("/login")
    public ApiResponse<? extends Serializable> login(@RequestBody LoginRq rq) {
        try {
            UserSessionEntity session = userService.loginByCredentials(rq.getLogin(), rq.getPassword());
            return success(
                    LoginRs.builder().token(session.getId().toString()).build()
            );
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return error("Пользователь с таким email или паролем не существует");
        } catch (Exception e) {
            LOGGER.error("Registration error: {}", e.getMessage());
            return error("Ошибка авторизации");
        }
    }

    @RequestMapping("/modules")
    public ApiResponse<? extends Serializable> modules() {
        return success(
                new ModulesRs(
                        modulesService.getUserModules(
                                auth.getGroup()
                        )
                )
        );
    }

}
