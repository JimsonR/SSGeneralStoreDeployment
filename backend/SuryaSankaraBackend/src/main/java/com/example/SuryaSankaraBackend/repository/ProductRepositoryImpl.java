package com.example.SuryaSankaraBackend.repository;

import com.azure.cosmos.models.CosmosPatchItemRequestOptions;
import com.azure.cosmos.models.CosmosPatchOperations;
import com.azure.cosmos.models.PartitionKey;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.core.query.CosmosQuery;
import com.azure.spring.data.cosmos.core.query.Criteria;
import com.azure.spring.data.cosmos.core.query.CriteriaType;
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
public class ProductRepositoryImpl implements ProductRepository{
    private static final String CONTAINER_NAME = "products";

    @Autowired
    private CosmosTemplate cosmosTemplate;

    @Override
    public <S extends Products> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        return cosmosTemplate.insert(
                CONTAINER_NAME,
                entity,
                new PartitionKey(entity.getCategory())  // Wrap category in PartitionKey
        );
    }

    @Override
    public List<Products> findByCategory(String category) {
        Criteria criteria = Criteria
                .getInstance(CriteriaType.IS_EQUAL, "category",
                        Collections.singletonList(category), Part.IgnoreCaseType.NEVER);
        CosmosQuery query = new CosmosQuery(criteria);
        return (List<Products>) cosmosTemplate.find(query, Products.class, CONTAINER_NAME);

    }

    @Override
    public List<Products> findByNameContaining(String name) {
       Criteria criteria = Criteria.getInstance(CriteriaType.CONTAINING, "name"
       , Collections.singletonList(name), Part.IgnoreCaseType.NEVER);
       return (List<Products>) cosmosTemplate.find(new CosmosQuery(criteria), Products.class,CONTAINER_NAME);

    }

    @Override
    public Optional<Products> findById(String id, PartitionKey partitionKey) {
        return Optional.ofNullable(
                cosmosTemplate.findById(id, Products.class, partitionKey)
        );
    }

    @Override
    public void deleteById(String id, PartitionKey partitionKey) {
        cosmosTemplate.deleteById(CONTAINER_NAME,id,partitionKey);

    }

    @Override
    public <S extends Products> S save(String s, PartitionKey partitionKey, Class<S> aClass, CosmosPatchOperations cosmosPatchOperations) {
        return null;
    }

    @Override
    public <S extends Products> S save(String s, PartitionKey partitionKey, Class<S> aClass, CosmosPatchOperations cosmosPatchOperations, CosmosPatchItemRequestOptions cosmosPatchItemRequestOptions) {
        return cosmosTemplate.patch(s,partitionKey,aClass,cosmosPatchOperations);
    }
    @Override
    public Iterable<Products> findAll(PartitionKey partitionKey) {
        return cosmosTemplate.findAll(partitionKey, Products.class);
    }
    @Override
    public Iterable<Products> findAll(Sort sort) {
        // Create empty criteria (matches all documents)
        Criteria criteria = Criteria.getInstance(CriteriaType.IS_EQUAL, "id",
                Collections.singletonList(""), Part.IgnoreCaseType.NEVER);

        // Alternative: Use the Criteria builder style
        // Criteria criteria = Criteria.where("id").is("").ignoreCase(false);

        // Create query with sort
        CosmosQuery query = new CosmosQuery(criteria).with(sort);

        return cosmosTemplate.find(query, Products.class, CONTAINER_NAME);
    }


    @Override
    public Page<Products> findAll(Pageable pageable) {
        // For paginated cross-partition queries
        return cosmosTemplate.findAll(pageable, Products.class, CONTAINER_NAME);
    }
}
