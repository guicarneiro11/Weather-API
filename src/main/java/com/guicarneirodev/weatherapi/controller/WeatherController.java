package com.guicarneirodev.weatherapi.controller;

import com.guicarneirodev.weatherapi.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapi.service.WeatherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherService weatherService;

    @PostMapping("/cities/{cityName}/users/{userId}")
    public ResponseEntity<WeatherDataDTO> addWeatherData(
            @PathVariable String cityName,
            @PathVariable String userId) {
        return ResponseEntity.ok(weatherService.addWeatherData(cityName, userId));
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<WeatherDataDTO> createWeatherData(
            @RequestBody @Valid WeatherDataDTO weatherDataDTO,
            @PathVariable String userId) {
        log.info("Creating weather data for user: {}", userId);
        return ResponseEntity.ok(weatherService.createWeatherData(weatherDataDTO, userId));
    }

    @GetMapping("/cities/{cityName}/users/{userId}/history")
    public ResponseEntity<WeatherDataDTO.ListResponseDTO> getWeatherHistory(
            @PathVariable String cityName,
            @PathVariable String userId) {
        return ResponseEntity.ok(weatherService.getWeatherHistory(cityName, userId));
    }

    @PutMapping("/favorites/{favoriteCityId}")
    public ResponseEntity<WeatherDataDTO> updateWeatherData(
            @PathVariable Long favoriteCityId) {
        return ResponseEntity.ok(weatherService.updateWeatherData(favoriteCityId));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<WeatherDataDTO>> getLatestWeatherForUser(
            @PathVariable String userId) {
        return ResponseEntity.ok(weatherService.getLatestWeatherForUser(userId));
    }
}
