package com.guicarneirodev.weatherapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class WeatherInfo {
    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(nullable = false)
    @DecimalMin("-273.15")
    private Double temperature;

    @Column(nullable = false)
    @Range(min = 0, max = 100)
    private Integer humidity;

    @Column(name = "wind_speed", nullable = false)
    @PositiveOrZero
    private Double windSpeed;

    @Column(nullable = false)
    @NotBlank
    private String description;

    @Column(name = "feels_like")
    private Double feelsLike;

    @Column
    private Integer pressure;

    @Column(name = "rain_probability")
    @Range(min = 0, max = 100)
    private Integer rainProbability;

    @Column(name = "weather_icon")
    private String weatherIcon;

    public static WeatherInfo create(String cityName, Double temperature, Integer humidity,
                                     Double windSpeed, String description) {
        return WeatherInfo.builder()
                .cityName(cityName)
                .temperature(temperature)
                .humidity(humidity)
                .windSpeed(windSpeed)
                .description(description)
                .build();
    }
}