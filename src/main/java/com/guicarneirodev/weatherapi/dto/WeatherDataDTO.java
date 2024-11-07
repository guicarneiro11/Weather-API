package com.guicarneirodev.weatherapi.dto;

import com.guicarneirodev.weatherapi.model.FavoriteCity;
import com.guicarneirodev.weatherapi.model.WeatherData;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class WeatherDataDTO {
    private final Long id;
    private final WeatherInfoDTO info;
    private final LocalDateTime createdAt;
    private final Long favoriteCityId;

    @Builder
    private WeatherDataDTO(Long id, WeatherInfoDTO info,
                           LocalDateTime createdAt, Long favoriteCityId) {
        this.id = id;
        this.info = Objects.requireNonNull(info, "Weather info cannot be null");
        this.createdAt = createdAt;
        this.favoriteCityId = favoriteCityId;
    }

    // Factory method
    public static WeatherDataDTO create(WeatherInfoDTO info, Long favoriteCityId) {
        return WeatherDataDTO.builder()
                .info(info)
                .favoriteCityId(favoriteCityId)
                .build();
    }

    public static WeatherDataDTO fromEntity(WeatherData weatherData) {
        return WeatherDataDTO.builder()
                .id(weatherData.getId())
                .info(WeatherInfoDTO.fromWeatherInfo(weatherData.getInfo()))
                .createdAt(weatherData.getCreatedAt())
                .favoriteCityId(weatherData.getFavoriteCity().getId())
                .build();
    }

    public WeatherData toEntity(FavoriteCity favoriteCity) {
        return new WeatherData(info.toWeatherInfo(), favoriteCity);
    }

    @Getter
    public static class ListResponseDTO {
        private final List<WeatherDataDTO> data;
        private final int count;

        private ListResponseDTO(List<WeatherDataDTO> data) {
            this.data = Collections.unmodifiableList(data);
            this.count = data.size();
        }

        public static ListResponseDTO of(List<WeatherData> weatherDataList) {
            List<WeatherDataDTO> dtos = weatherDataList.stream()
                    .map(WeatherDataDTO::fromEntity)
                    .collect(Collectors.toList());
            return new ListResponseDTO(dtos);
        }
    }
}