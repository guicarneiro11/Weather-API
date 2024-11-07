package com.guicarneirodev.weatherapi.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "openweathermap.api")
@Validated
@Getter
@Setter
public class WeatherProperties {

    @NotBlank(message = "API key must be provided")
    private String key;

    @NotBlank(message = "API base URL must be provided")
    private String url;

    @Min(value = 1000, message = "Connection timeout must be at least 1000ms")
    @Max(value = 10000, message = "Connection timeout cannot exceed 10000ms")
    private int connectionTimeout = 3000;

    @Min(value = 1000, message = "Read timeout must be at least 1000ms")
    @Max(value = 10000, message = "Read timeout cannot exceed 10000ms")
    private int readTimeout = 5000;

    @Min(value = 1, message = "Max retry attempts must be at least 1")
    @Max(value = 5, message = "Max retry attempts cannot exceed 5")
    private int maxRetryAttempts = 3;

    @Min(value = 60, message = "Cache duration must be at least 60 seconds")
    @Max(value = 3600, message = "Cache duration cannot exceed 3600 seconds")
    private int cacheDuration = 300;

    // Configurações de rate limiting
    @Min(value = 1, message = "Rate limit must be at least 1 request per minute")
    private int rateLimit = 60;

    @Min(value = 1, message = "Rate limit window must be at least 1 minute")
    private int rateLimitWindowMinutes = 1;
}
