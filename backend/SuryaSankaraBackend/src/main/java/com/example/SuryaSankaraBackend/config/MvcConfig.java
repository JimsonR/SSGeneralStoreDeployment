package com.example.SuryaSankaraBackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
//        resourceHandlerRegistry.addResourceHandler("/api/uploads/**")
//                .addResourceLocations("file:C:\\New folder (2)\\suryashankaraproject\\backend\\SSgeneralsBackend\\SuryaSankaraBackend\\uploads\\");
//    }
@Value("${file.upload-dir}") // Define this in application.properties
private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/**") // Changed to match your URL pattern
                .addResourceLocations("file:" + uploadDir + "/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
