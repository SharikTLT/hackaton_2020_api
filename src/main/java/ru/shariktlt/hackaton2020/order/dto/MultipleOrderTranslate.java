package ru.shariktlt.hackaton2020.order.dto;

import lombok.experimental.SuperBuilder;
import ru.shariktlt.hackaton2020.core.dto.PaginatedResponse;

@SuperBuilder
public class MultipleOrderTranslate extends PaginatedResponse<SingleOrderTranlsate> {
}
