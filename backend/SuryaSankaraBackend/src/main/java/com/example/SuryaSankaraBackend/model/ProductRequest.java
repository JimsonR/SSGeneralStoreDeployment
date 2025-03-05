package com.example.SuryaSankaraBackend.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Builder
@Data
public class ProductRequest {

    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private String imageUrl;

}
