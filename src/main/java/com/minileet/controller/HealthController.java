package com.minileet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "app", "mini-leet-backend");
    }

    @GetMapping("/")
    public String home() {
        return "Mini-Leet backend running";
    }
}
