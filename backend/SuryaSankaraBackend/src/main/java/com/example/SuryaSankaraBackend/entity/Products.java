package com.example.SuryaSankaraBackend.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//
//public class Products {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    private String name;
//    private String category;
//    private BigDecimal price;
//    private String imageUrl;
//
//
//
//}
@Container(containerName = "products") // Cosmos DB container name
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products {
    @Id
    private String id;  // Changed from long to String

    @PartitionKey      // Important for Cosmos DB performance
    private String category;

    private String name;
//    private BigDecimal price;
    private String price;
    private String imageUrl;
}
