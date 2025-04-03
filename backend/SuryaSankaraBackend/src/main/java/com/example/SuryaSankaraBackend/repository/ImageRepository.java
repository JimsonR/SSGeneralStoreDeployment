package com.example.SuryaSankaraBackend.repository;


import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CosmosRepository<ImageEntity, String> {
}
