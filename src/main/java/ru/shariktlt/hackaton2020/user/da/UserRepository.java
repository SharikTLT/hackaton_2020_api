package ru.shariktlt.hackaton2020.user.da;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    long countByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAll(Pageable pageable);

}
