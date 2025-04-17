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
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> allProducts(@RequestParam int pgNo , @RequestParam int pgSize){
        return ResponseEntity.ok(productService.getAllProducts( pgNo , pgSize));

    }

//    @GetMapping("/all/product-detail")
//    public ResponseEntity<ProductResponse> getProduct(@RequestParam long id){
//        return ResponseEntity.ok(productService.getProduct(id));
//    }
}
