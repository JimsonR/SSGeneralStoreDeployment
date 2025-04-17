package com.example.SuryaSankaraBackend.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

@Container(containerName = "images")
public class ImageEntity {

    @Id
    private String id;
    private String imageUrl;

    @PartitionKey
    private String uploadedBy;

    // Default constructor required by Spring Data
    public ImageEntity() {
    }

    public ImageEntity(String id, String imageUrl, String uploadedBy) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
