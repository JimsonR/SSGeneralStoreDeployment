package com.example.SuryaSankaraBackend.controller;

import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.example.SuryaSankaraBackend.repository.ImageRepository;
import com.example.SuryaSankaraBackend.service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("api/images")
public class ImageUploadController {
//    private static final String UPLOAD_DIR = "uploads/";
    private static final String UPLOAD_DIR = "/var/www/uploads/";

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
//        try {
//            // Ensure directory exists
//            Path uploadPath = Paths.get(UPLOAD_DIR);
//            if (!Files.exists(uploadPath)) {
//                Files.createDirectories(uploadPath);
//            }
//
//            // Get clean file name
//            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
//            Path filePath = uploadPath.resolve(fileName);
//
//            // Save the file
//            Files.write(filePath, file.getBytes());
//
//            // Return the full URL (assuming static folder configuration)
//            String imageUrl = "/api/uploads/" + fileName;
//            return ResponseEntity.ok(imageUrl);
//
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().body("Failed to upload image");
//        }
//
//    }
@Autowired
    private AzureBlobStorageService blobStorageService;
    @Autowired
    private ImageRepository imageRepository;



//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
//        try {
//            String imageUrl = blobStorageService.uploadFile(file);
//
//            // Save metadata to Cosmos DB
//            ImageEntity imageEntity = new ImageEntity(UUID.randomUUID().toString(), imageUrl, "Jyothika");
//            imageRepository.save(imageEntity);
//
//            return ResponseEntity.ok(imageUrl);
//        } catch (IOException e) {
//            return ResponseEntity.badRequest().body("Failed to upload image");
//        }
//    }
@PostMapping("/upload")
public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file ,
                                          @RequestParam String category) {
    try {
        String imageUrl = blobStorageService.uploadFile(file,category);

        // Save metadata to Cosmos DB
        ImageEntity imageEntity = new ImageEntity(UUID.randomUUID().toString(), imageUrl, "Jyothika");
        imageRepository.save(imageEntity);

        return ResponseEntity.ok(imageUrl);
    } catch (IOException e) {
        return ResponseEntity.badRequest().body("Failed to upload image");
    }
}

}