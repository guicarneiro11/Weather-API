package com.guicarneirodev.weatherapi.dto;

import com.guicarneirodev.weatherapi.model.WeatherInfo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherInfoDTO {
    @NotBlank(message = "City name is required")
    private String cityName;

    @NotNull(message = "Temperature is required")
    @DecimalMin(value = "-273.15", message = "Temperature cannot be below absolute zero")
    private Double temperature;

    @NotNull(message = "Humidity is required")
    @Range(min = 0, max = 100, message = "Humidity must be between 0 and 100")
    private Integer humidity;

    @NotNull(message = "Wind speed is required")
    @PositiveOrZero(message = "Wind speed cannot be negative")
    private Double windSpeed;

    @NotBlank(message = "Weather description is required")
    private String description;

    private Double feelsLike;
    private Integer pressure;

    @Range(min = 0, max = 100, message = "Rain probability must be between 0 and 100")
    private Integer rainProbability;

    private String weatherIcon;

    // Factory method mantido
    public static WeatherInfoDTO create(String cityName, Double temperature,
                                        Integer humidity, Double windSpeed,
                                        String description) {
        return WeatherInfoDTO.builder()
                .cityName(cityName)
                .temperature(temperature)
                .humidity(humidity)
                .windSpeed(windSpeed)
                .description(description)
                .build();
    }

    public WeatherInfo toWeatherInfo() {
        return WeatherInfo.builder()
                .cityName(this.cityName)
                .temperature(this.temperature)
                .humidity(this.humidity)
                .windSpeed(this.windSpeed)
                .description(this.description)
                .feelsLike(this.feelsLike)
                .pressure(this.pressure)
                .rainProbability(this.rainProbability)
                .weatherIcon(this.weatherIcon)
                .build();
    }

    public static WeatherInfoDTO fromWeatherInfo(WeatherInfo info) {
        return WeatherInfoDTO.builder()
                .cityName(info.getCityName())
                .temperature(info.getTemperature())
                .humidity(info.getHumidity())
                .windSpeed(info.getWindSpeed())
                .description(info.getDescription())
                .feelsLike(info.getFeelsLike())
                .pressure(info.getPressure())
                .rainProbability(info.getRainProbability())
                .weatherIcon(info.getWeatherIcon())
                .build();
    }
}