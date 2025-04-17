package com.example.SuryaSankaraBackend.service;

import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.example.SuryaSankaraBackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ImageService {
    @Autowired
    private ImageRepository imageRepository;


    public ImageEntity saveImageMetadata(String imageUrl, String uploadedBy) {
        ImageEntity imageEntity = new ImageEntity(
                UUID.randomUUID().toString(),
                imageUrl,
                uploadedBy
        );
        return imageRepository.save(imageEntity);
    }

    public List<ImageEntity> getImagesByUploader(String uploadedBy) {
        return imageRepository.findByUploadedBy(uploadedBy);
    }


}
