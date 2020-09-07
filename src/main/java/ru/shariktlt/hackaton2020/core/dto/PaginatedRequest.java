package ru.shariktlt.hackaton2020.core.dto;

import lombok.Getter;
import lombok.Setter;

public class PaginatedRequest {
    @Getter
    @Setter
    private int offset;

    @Getter
    @Setter
    private int max;
}
