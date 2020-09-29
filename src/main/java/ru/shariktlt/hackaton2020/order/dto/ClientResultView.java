package ru.shariktlt.hackaton2020.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientResultView {
    private String title;

    private String desc;
}
