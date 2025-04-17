package com.example.SuryaSankaraBackend.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.SuryaSankaraBackend.entity.ImageEntity;
import com.example.SuryaSankaraBackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public interface ProductRepository extends JpaRepository<Products,Long> {
//}
@Repository
public interface ProductRepository extends CosmosRepository<Products, String> {
    <S extends Products> S save(S entity);

    // Custom queries
    List<Products> findByCategory(String category);
    List<Products> findByNameContaining(String name);
}
