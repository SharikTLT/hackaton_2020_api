package ru.shariktlt.hackaton2020.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.exception.ApiForbidden;
import ru.shariktlt.hackaton2020.core.exception.ApiUnauthorized;

import javax.inject.Provider;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorizationService {
    @Autowired
    private Provider<ClientApiContext> apiContextProvider;

    public ClientApiContext getCtx() {
        ClientApiContext ctx = apiContextProvider.get();
        if (!ctx.isAuthorized()) {
            throw new ApiForbidden();
        }
        return ctx;
    }

    public boolean inGroup(GroupsEnum... groups) {
        ClientApiContext ctx = getCtx();
        return Arrays.stream(groups)
                .map(GroupsEnum::getName)
                .map(ctx::inGroup)
                .filter(b -> !b)
                .count() == 0;
    }

    public void checkAccess(GroupsEnum... groups) {
        if (!inGroup(groups)) {
            throw new ApiUnauthorized();
        }
    }

    public List<GroupsEnum> getGroup() {
        ClientApiContext ctx = apiContextProvider.get();
        if (!ctx.isAuthorized()) {
            throw new ApiForbidden();
        }
        return ctx.getGroups().stream().map(GroupsEnum::getByName).collect(Collectors.toList());
    }
}
