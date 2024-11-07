package com.guicarneirodev.weatherapi.controller;

import com.guicarneirodev.weatherapi.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapi.service.FavoriteCityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteCityController {
    private final FavoriteCityService service;

    @PostMapping
    public ResponseEntity<FavoriteCityDTO> addFavorite(
            @RequestBody @Valid FavoriteCityDTO dto) {
        return ResponseEntity.ok(service.addFavorite(dto));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteCityDTO>> getFavorites(
            @RequestParam String userId) {
        return ResponseEntity.ok(service.getFavoritesByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteCityDTO> getFavorite(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFavorite(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        service.deleteFavorite(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FavoriteCityDTO> updateFavorite(
            @PathVariable Long id,
            @RequestBody @Valid FavoriteCityDTO dto) {
        return ResponseEntity.ok(service.updateFavorite(id, dto));
    }
}