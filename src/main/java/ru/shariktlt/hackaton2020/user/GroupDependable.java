package ru.shariktlt.hackaton2020.user;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.TriConsumer;
import ru.shariktlt.hackaton2020.core.enums.GroupsEnum;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;

import java.util.LinkedList;
import java.util.List;

public class GroupDependable<ENTITY, DTO> {

    private List<ItemDependable> items = new LinkedList<>();

    public void add(GroupsEnum groupsEnum, TriConsumer<ENTITY, DTO, ClientApiContext> item) {
        this.items.add(new ItemDependable(groupsEnum.getName(), item));
    }


    public void add(String group, TriConsumer<ENTITY, DTO, ClientApiContext> item) {
        this.items.add(new ItemDependable(group, item));
    }

    public DTO process(ENTITY entity, DTO dto, ClientApiContext ctx) {
        items.forEach(item -> {
            if (ctx.inGroup(item.group)) {
                item.triConsumer.accept(entity, dto, ctx);
            }
        });
        return dto;
    }

    @AllArgsConstructor
    public class ItemDependable {
        final String group;
        final TriConsumer<ENTITY, DTO, ClientApiContext> triConsumer;
    }
}
