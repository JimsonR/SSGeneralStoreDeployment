package com.example.SuryaSankaraBackend.config;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
        resourceHandlerRegistry.addResourceHandler("/api/uploads/**")
                .addResourceLocations("file:C:\\New folder (2)\\suryashankaraproject\\backend\\SSgeneralsBackend\\SuryaSankaraBackend\\uploads\\");
    }
}
