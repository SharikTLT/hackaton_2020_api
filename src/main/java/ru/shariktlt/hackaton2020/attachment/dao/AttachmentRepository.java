package ru.shariktlt.hackaton2020.attachment.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.shariktlt.hackaton2020.attachment.entity.AttachmentEntity;

import java.util.List;
import java.util.UUID;

public interface AttachmentRepository extends CrudRepository<AttachmentEntity, UUID> {

    List<AttachmentEntity> findByPackageId(UUID packageId, Pageable pagable);

}
