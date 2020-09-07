package ru.shariktlt.hackaton2020.user.dto;

import lombok.experimental.SuperBuilder;
import ru.shariktlt.hackaton2020.core.dto.PaginatedResponse;

@SuperBuilder
public class UserListRs extends PaginatedResponse<UserDto> {

}
