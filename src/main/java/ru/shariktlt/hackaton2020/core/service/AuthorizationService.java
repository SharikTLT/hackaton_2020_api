package ru.shariktlt.hackaton2020.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.exception.ApiForbidden;
import ru.shariktlt.hackaton2020.core.exception.ApiUnauthorized;

import javax.inject.Provider;
import java.util.Arrays;

@Component
public class AuthorizationService {
    @Autowired
    private Provider<ClientApiContext> apiContextProvider;

    public boolean inGroup(GroupsEnum... groups) {
        ClientApiContext ctx = apiContextProvider.get();
        if (!ctx.isAuthorized()) {
            throw new ApiForbidden();
        }
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
        inGroup(groups);
    }
}
