package ru.shariktlt.hackaton2020.user.da;

import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.hackaton2020.user.entity.UserSessionEntity;

import java.util.UUID;

public interface UserSessionRepository extends CrudRepository<UserSessionEntity, UUID> {
}
