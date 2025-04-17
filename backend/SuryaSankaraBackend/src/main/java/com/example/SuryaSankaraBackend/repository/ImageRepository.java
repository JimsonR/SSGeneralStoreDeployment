package com.example.SuryaSankaraBackend.repository;


import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CosmosRepository<ImageEntity, String> {

    <S extends  ImageEntity> S save(S entity);

    List<ImageEntity> findByUploadedBy(String uploadedBy);

}
