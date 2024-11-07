package com.guicarneirodev.weatherapi.dto;

import com.guicarneirodev.weatherapi.model.FavoriteCity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteCityDTO {
    private long id;

    @NotBlank(message = "City name is required")
    private String cityName;

    @NotBlank(message = "User ID is required")
    private String userId;

    private LocalDateTime createAt;

    public static FavoriteCityDTO fromEntity(FavoriteCity entity) {
        return FavoriteCityDTO.builder()
                .id(entity.getId())
                .cityName(entity.getCityName())
                .userId(entity.getUserId())
                .createAt(entity.getCreatedAt())
                .build();
    }

    public FavoriteCity toEntity() {
        return FavoriteCity.builder()
                .cityName(this.cityName)
                .userId(this.userId)
                .build();
    }
}
