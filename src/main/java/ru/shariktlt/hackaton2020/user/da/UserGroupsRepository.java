package ru.shariktlt.hackaton2020.user.da;

import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.hackaton2020.user.entity.UserGroupCompositId;
import ru.shariktlt.hackaton2020.user.entity.UserGroupsEntity;

import java.util.Set;

public interface UserGroupsRepository extends CrudRepository<UserGroupsEntity, UserGroupCompositId> {

    Set<UserGroupsEntity> findByUserId(long userId);

}
