package com.example.SuryaSankaraBackend.config;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.core.convert.MappingCosmosConverter;
import com.azure.spring.data.cosmos.core.mapping.CosmosMappingContext;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;

@Configuration
@EnableCosmosRepositories
public class CosmosConfiguration {
    @Value("${azure.cosmos.uri}")
    private String uri;

    @Value("${azure.cosmos.key}")
    private String key;

    @Value("${azure.cosmos.database}")
    private String database;
    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        return new CosmosClientBuilder()
                .endpoint(uri)
                .key(key)
                .contentResponseOnWriteEnabled(true)
                .preferredRegions(Collections.singletonList("West US 3")) // Match your region
                .directMode();
    }



    @Bean
    @Primary
    public CosmosAsyncClient getCosmosAsyncClient(CosmosClientBuilder cosmosClientBuilder){
        return  cosmosClientBuilder.buildAsyncClient();
    }

    @Bean

    public com.azure.spring.data.cosmos.config.CosmosConfig cosmosConfig(){
        return com.azure.spring.data.cosmos.config.CosmosConfig.builder()
                .enableQueryMetrics(true).build();
    }
    @Bean
    public MappingCosmosConverter mappingCosmosConverter(CosmosMappingContext cosmosMappingContext) {
        return new MappingCosmosConverter(cosmosMappingContext, null);
    }
    @Bean
    public CosmosTemplate cosmosTemplate(CosmosAsyncClient cosmosAsyncClient,
                                         com.azure.spring.data.cosmos.config.CosmosConfig cosmosConfig,
                                         MappingCosmosConverter mappingCosmosConverter){
        return new CosmosTemplate(cosmosAsyncClient,database,cosmosConfig,mappingCosmosConverter);
    }
    @Bean
    public CosmosMappingContext cosmosMappingContext() {
        return new CosmosMappingContext();
    }


}
