package ru.shariktlt.hackaton2020.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * https://stackabuse.com/uploading-files-with-spring-boot/
 */
@Service
public class FileService {

    public static final String UPLOADED_PACKAGES = "uploadedPackages";
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public String uploadFile(MultipartFile file, UUID packageId) {
        String orignalName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String path = String.join(File.separator, uploadDir, UPLOADED_PACKAGES, packageId.toString());
            Files.createDirectories(Paths.get(path));
            Path copyLocation = Paths
                    .get(String.join(File.separator, path, orignalName));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
        return orignalName;
    }
}