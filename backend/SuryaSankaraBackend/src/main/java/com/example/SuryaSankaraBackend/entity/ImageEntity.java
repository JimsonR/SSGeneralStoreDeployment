package com.example.SuryaSankaraBackend.entity;


import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;

@Container(containerName = "images")
public class ImageEntity {

    @Id
    private String id;
    private String imageUrl;
    private String uploadedBy;

    public ImageEntity(String id, String imageUrl, String uploadedBy) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
}
