package ru.shariktlt.hackaton2020.attachment.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(name = "ATTACHMENTS")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AttachmentEntity {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @Column(name = "UPLOADER_ID", nullable = false)
    private Long uploaderId;

    @Column(name = "ORIGINAL_NAME", nullable = false)
    private String originalName;

    @Column(name = "UPLOADED")
    private GregorianCalendar uploaded;

    @Column(name = "PACKAGE_ID")
    private UUID packageId;
}
