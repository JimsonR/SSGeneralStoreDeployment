package com.example.SuryaSankaraBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/health")
public class HealthEndpoint {
    @GetMapping("/")
    public String imHealthy() {
        return "{healthy:true}";
    }
    @GetMapping("/readiness")
    public String imReady() {
        return "{healthy:true}";
    }
    @GetMapping("/liveness")
    public String imLive() {
        return "{healthy:true}";
    }
}
