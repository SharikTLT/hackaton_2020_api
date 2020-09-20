package ru.shariktlt.hackaton2020.attachment.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class AttachmentUploadRs implements Serializable {
    private UUID uuid;
    private AttachmentStatus status;
}
