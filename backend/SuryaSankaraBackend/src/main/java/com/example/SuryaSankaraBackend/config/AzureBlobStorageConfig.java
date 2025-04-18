package com.example.SuryaSankaraBackend.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    @Value("${azure.storage.account-name}")
    private String accountName;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient() {
        BlobContainerClient client = blobServiceClient().getBlobContainerClient(containerName);
        if (!client.exists()) {
            client.create();
        }
        return client;
    }
    @Bean
    public String storageBaseUrl() {
        // Automatically resolves to geographic endpoint
        return String.format("https://%s.z.web.core.windows.net/%s",
                accountName, containerName);
    }

}
