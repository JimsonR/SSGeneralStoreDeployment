package com.example.SuryaSankaraBackend.service;

import com.example.SuryaSankaraBackend.entity.Products;
import com.example.SuryaSankaraBackend.model.ProductRequest;
import com.example.SuryaSankaraBackend.model.ProductResponse;
import com.example.SuryaSankaraBackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
@Autowired
    private ProductRepository productRepository;

//public String upsertProduct(ProductRequest productRequest){
//    if(productRequest.getId() != null && productRepository.existsById(productRequest.getId())){
//        Products existingProduct = productRepository.getReferenceById(productRequest.getId());
//        existingProduct.setName( productRequest.getName().isEmpty()?existingProduct.getName():productRequest.getName());
//        existingProduct.setCategory(productRequest.getCategory().isEmpty()?existingProduct.getCategory():productRequest.getCategory());
//        existingProduct.setPrice(productRequest.getPrice()==null?existingProduct.getPrice():productRequest.getPrice());
//        existingProduct.setImageUrl(productRequest.getImageUrl().isEmpty()?existingProduct.getImageUrl():productRequest.getImageUrl());
//        productRepository.save(existingProduct);
//    return "product updated Successfully";
//    }
//    Products newProduct = Products.builder()
//            .name(productRequest.getName())
//            .category(productRequest.getCategory())
//            .price(productRequest.getPrice())
//            .imageUrl(productRequest.getImageUrl()).build();
//    productRepository.save(newProduct);
//    return "product added successfully";
//
//}
//
//public List<ProductResponse> allProducts(){
//    List<ProductResponse> productResponseList = new ArrayList<>();
//for (Products product : productRepository.findAll()){
//    ProductResponse productResponse = ProductResponse.builder()
//            .id(product.getId())
//            .name(product.getName())
//            .category(product.getCategory())
//            .price(product.getPrice())
//            .imageUrl(product.getImageUrl()).build();
//    productResponseList.add(productResponse);
//}
//return productResponseList;
//}
//
//public ProductResponse getProduct(long id){
//    if(productRepository.existsById(id)) {
//        Products product = productRepository.findById(id).get();
//        ProductResponse productResponse = new ProductResponse();
//        productResponse.setId(product.getId());
//        productResponse.setName(product.getName());
//        productResponse.setCategory(product.getCategory());
//        productResponse.setPrice(product.getPrice());
//        productResponse.setImageUrl(product.getImageUrl());
//        return productResponse;
//    }
//    throw new RuntimeException();
//}
public String saveProduct(ProductRequest product) {
  Products products = Products.builder().name(product.getName()).price(product.getPrice()+"").category(product.getCategory()).build();
     productRepository.save(products);
     return "product added successfully";
}

    public List<ProductResponse> getAllProducts(int pgNo , int pgSize ) {
        Pageable pageable = PageRequest.of(pgNo,pgSize);
       List<ProductResponse> productResponses = new ArrayList<>();
        for (Products productResponse : productRepository.findAll(pageable).stream().toList()){
         productResponses.add(ProductResponse.builder()
                   .id(productResponse.getId())
                   .price(BigDecimal.valueOf(Double.parseDouble(productResponse.getPrice())))
                   .name(productResponse.getName())
                   .category(productResponse.getCategory())
                   .imageUrl(productResponse.getImageUrl()).build());
       }
        return productResponses;
    }

    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
