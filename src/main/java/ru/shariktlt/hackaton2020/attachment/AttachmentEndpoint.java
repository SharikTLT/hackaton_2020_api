package ru.shariktlt.hackaton2020.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shariktlt.hackaton2020.attachment.dto.AttachmentStatus;
import ru.shariktlt.hackaton2020.attachment.dto.AttachmentUploadRs;
import ru.shariktlt.hackaton2020.core.dto.ApiResponse;
import ru.shariktlt.hackaton2020.core.service.AuthorizationService;
import ru.shariktlt.hackaton2020.core.service.ClientApiContext;

import java.io.Serializable;

import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.error;
import static ru.shariktlt.hackaton2020.core.dto.ApiResponse.success;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/attachment")
public class AttachmentEndpoint {

    @Autowired
    private AuthorizationService auth;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ApiResponse<? extends Serializable> upload(@RequestParam MultipartFile[] files) {
        ClientApiContext ctx = auth.getCtx();
        try {
            AttachmentUploadRs rs = new AttachmentUploadRs();
            rs.setUuid(attachmentService.preparePackageAndSave(files, ctx.getUserEntity()));
            rs.setStatus(AttachmentStatus.READY);
            return success(rs);
        } catch (Exception e) {
            return error("Ошибка загрузки");
        }
    }
}
