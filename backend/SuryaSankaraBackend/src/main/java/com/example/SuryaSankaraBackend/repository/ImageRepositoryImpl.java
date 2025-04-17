package com.example.SuryaSankaraBackend.repository;

import com.azure.cosmos.models.CosmosPatchItemRequestOptions;
import com.azure.cosmos.models.CosmosPatchOperations;
import com.azure.cosmos.models.PartitionKey;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.core.query.CosmosQuery;
import com.azure.spring.data.cosmos.core.query.Criteria;
import com.azure.spring.data.cosmos.core.query.CriteriaType;
import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.example.SuryaSankaraBackend.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ImageRepositoryImpl implements ImageRepository{
    @Autowired
    private CosmosTemplate cosmosTemplate;
    private static final String CONTAINER_NAME = "images";


    @Override
    public ImageEntity save(ImageEntity entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        return cosmosTemplate.insert(CONTAINER_NAME, entity, new PartitionKey(entity.getUploadedBy()));
    }

    @Override
    public List<ImageEntity> findByUploadedBy(String uploadedBy) {
        Criteria criteria = Criteria.getInstance(CriteriaType.IS_EQUAL, "uploadedBy",
                Collections.singletonList(uploadedBy), Part.IgnoreCaseType.NEVER);
        CosmosQuery query = new CosmosQuery(criteria);
        return (List<ImageEntity>) cosmosTemplate.find(query, ImageEntity.class, CONTAINER_NAME);
    }

    @Override
    public Optional<ImageEntity> findById(String id, PartitionKey partitionKey) {
        return Optional.ofNullable(
                cosmosTemplate.findById(id, ImageEntity.class, partitionKey)
        );
    }

    @Override
    public void deleteById(String id, PartitionKey partitionKey) {
        cosmosTemplate.deleteById(CONTAINER_NAME, id, partitionKey);
    }

    @Override
    public <S extends ImageEntity> S save(String s, PartitionKey partitionKey, Class<S> aClass, CosmosPatchOperations cosmosPatchOperations) {
        return null;
    }

    @Override
    public <S extends ImageEntity> S save(String s, PartitionKey partitionKey, Class<S> aClass, CosmosPatchOperations cosmosPatchOperations, CosmosPatchItemRequestOptions cosmosPatchItemRequestOptions) {
        return null;
    }

    @Override
    public Iterable<ImageEntity> findAll(PartitionKey partitionKey) {
        return null;
    }

    @Override
    public Iterable<ImageEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ImageEntity> findAll(Pageable pageable) {
        return cosmosTemplate.findAll(pageable, ImageEntity.class, CONTAINER_NAME);
    }
}
