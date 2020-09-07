package ru.shariktlt.hackaton2020.core.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@SuperBuilder
public class PaginatedResponse<T extends Serializable> implements Serializable {
    @Getter
    @Setter
    private Integer offset;

    @Getter
    @Setter
    private Long total;

    @Getter
    @Setter
    private List<T> items;
}
