package com.gdsc.remine.global.gcs.service;

import com.gdsc.remine.global.gcs.FilePath;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoogleCloudStorageService {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    public String saveFile(
            final FilePath filePath,
            final MultipartFile multipartFile
    ) {
        String objectName = filePath.getFilePath() + "/" + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();

        try {
            storage.create(
                    BlobInfo.newBuilder(bucketName, objectName)
                            .setContentType(contentType)
                            .build(),
                    multipartFile.getInputStream()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "https://storage.googleapis.com/remine-declaration-bucket" + "/" + objectName;
    }
}
