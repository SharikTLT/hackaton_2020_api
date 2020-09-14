package ru.shariktlt.hackaton2020.user;

import org.springframework.stereotype.Component;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.user.dto.Modules;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModulesService {
    public List<Modules> getUserModules(List<GroupsEnum> groupsEnums) {
        return Arrays.stream(Modules.values()).filter(module -> available(module, groupsEnums)).collect(Collectors.toList());
    }

    private boolean available(Modules module, List<GroupsEnum> groupsEnums) {
        Set<GroupsEnum> clientGroups = new HashSet<>(groupsEnums);
        clientGroups.add(GroupsEnum.ANY);
        Set<GroupsEnum> availableFor = module.getGroupsEnums();
        return clientGroups.stream().filter(availableFor::contains).count() > 0;
    }
}
