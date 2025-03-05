package com.example.SuryaSankaraBackend.controller;

import com.example.SuryaSankaraBackend.model.ProductRequest;
import com.example.SuryaSankaraBackend.model.ProductResponse;
import com.example.SuryaSankaraBackend.repository.ProductRepository;
import com.example.SuryaSankaraBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/upsert")
    public ResponseEntity<String> upsertProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.upsertProduct(productRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> allProducts(){
        return ResponseEntity.ok(productService.allProducts());
    }

    @GetMapping("/all/product-detail")
    public ResponseEntity<ProductResponse> getProduct(@RequestParam long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
}
