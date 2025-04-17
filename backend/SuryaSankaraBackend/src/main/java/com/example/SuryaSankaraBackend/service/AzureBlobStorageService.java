package com.example.SuryaSankaraBackend.service;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AzureBlobStorageService {


    private final BlobContainerClient blobContainerClient;

    public AzureBlobStorageService(BlobContainerClient blobContainerClient) {
        this.blobContainerClient = blobContainerClient;
    }

    public String uploadFile(MultipartFile file, String prefix) throws IOException {
        // Generate unique filename with original extension
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = prefix + "/" + UUID.randomUUID() + fileExtension;

        // Upload with content type
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return blobClient.getBlobUrl();
    }

    public byte[] downloadFile(String blobName) {
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blobClient.download(outputStream);
        return outputStream.toByteArray();
    }

    public void deleteFile(String blobName) {
        blobContainerClient.getBlobClient(blobName).delete();
    }

    public List<String> listFiles(String prefix) {
        return blobContainerClient.listBlobsByHierarchy(prefix)
                .stream()
                .map(BlobItem::getName)
                .collect(Collectors.toList());
    }
}

