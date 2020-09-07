package ru.shariktlt.hackaton2020.core.service;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.user.UserService;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Component
@Scope("request")
@Getter
@Setter
public class ClientApiContext {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientApiContext.class);

    private UserEntity userEntity;

    private Set<String> groups;

    private boolean authorized;

    @Autowired
    public ClientApiContext(UserService userService, HttpServletRequest request) {
        try {
            String token = request.getHeader("api-token");
            if (isNotEmpty(token)) {
                userEntity = userService.getUserByToken(UUID.fromString(token));
                groups = userService.getUserGroups(userEntity.getId());
                authorized = true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public boolean inGroup(String group) {
        return isAuthorized() && groups.contains(group);
    }
}
