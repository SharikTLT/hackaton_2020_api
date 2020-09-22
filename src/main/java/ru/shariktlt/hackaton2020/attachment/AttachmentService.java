package ru.shariktlt.hackaton2020.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.shariktlt.hackaton2020.attachment.dao.AttachmentRepository;
import ru.shariktlt.hackaton2020.attachment.entity.AttachmentEntity;
import ru.shariktlt.hackaton2020.core.service.FileService;
import ru.shariktlt.hackaton2020.user.entity.UserEntity;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private FileService fileService;

    public UUID preparePackageAndSave(MultipartFile[] files, UserEntity uploader) {
        UUID packageId = UUID.randomUUID();
        List<AttachmentEntity> attachmentEntityList = Arrays.stream(files)
                .map(file -> renameAndStore(file, packageId, uploader))
                .collect(Collectors.toList());
        attachmentRepository.saveAll(attachmentEntityList);
        return packageId;
    }

    private AttachmentEntity renameAndStore(MultipartFile file, UUID packageId, UserEntity uploader) {

        return AttachmentEntity.builder()
                .id(UUID.randomUUID())
                .originalName(fileService.uploadFile(file, packageId))
                .uploaded(new GregorianCalendar())
                .packageId(packageId)
                .uploaderId(uploader.getId())
                .build();
    }

}
