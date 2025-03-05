package com.example.SuryaSankaraBackend.repository;

import com.example.SuryaSankaraBackend.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
}
