package com.guicarneirodev.weatherapi.service;

import com.guicarneirodev.weatherapi.dto.WeatherDataDTO;
import com.guicarneirodev.weatherapi.exception.BusinessException;
import com.guicarneirodev.weatherapi.exception.ResourceNotFoundException;
import com.guicarneirodev.weatherapi.exception.UnauthorizedAccessException;
import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapClient;
import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapResponse;
import com.guicarneirodev.weatherapi.model.FavoriteCity;
import com.guicarneirodev.weatherapi.model.WeatherData;
import com.guicarneirodev.weatherapi.model.WeatherInfo;
import com.guicarneirodev.weatherapi.repository.FavoriteCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
    private final OpenWeatherMapClient weatherMapClient;
    private final FavoriteCityRepository favoriteCityRepository;

    @Transactional
    public WeatherDataDTO addWeatherData(String cityName, String userId) {
        log.info("Adding weather data for city: {} and user: {}", cityName, userId);

        FavoriteCity favoriteCity = favoriteCityRepository
                .findByCityNameAndUserId(cityName, userId)
                .orElseGet(() -> new FavoriteCity(cityName, userId));

        OpenWeatherMapResponse weatherMapResponse = weatherMapClient.getWeatherData(cityName);

        WeatherInfo weatherInfo = WeatherInfo.create(
                cityName,
                weatherMapResponse.getMain().getTemp(),
                weatherMapResponse.getMain().getHumidity(),
                weatherMapResponse.getWind().getSpeed(),
                weatherMapResponse.getWeather().get(0).getDescription()
        );

        WeatherData weatherData = new WeatherData(weatherInfo, favoriteCity);
        favoriteCity.addWeatherData(weatherData);

        FavoriteCity savedCity = favoriteCityRepository.save(favoriteCity);
        WeatherData savedWeatherData = savedCity.getWeatherData()
                .get(savedCity.getWeatherData().size() - 1);

        return WeatherDataDTO.fromEntity(savedWeatherData);
    }

    @Transactional
    public WeatherDataDTO.ListResponseDTO getWeatherHistory(String cityName, String userId) {
        log.info("Getting weather history for city: {} and user: {}", cityName, userId);

        FavoriteCity favoriteCity = favoriteCityRepository
                .findByCityNameAndUserId(cityName, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No favorite city found with name: " + cityName));

        return WeatherDataDTO.ListResponseDTO.of(favoriteCity.getWeatherData());
    }

    @Transactional
    public WeatherDataDTO updateWeatherData(Long favoriteCityId) {
        log.info("Updating weather data for favorite city ID: {}", favoriteCityId);

        FavoriteCity favoriteCity = favoriteCityRepository.findById(favoriteCityId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Favorite city not found with ID: " + favoriteCityId));

        OpenWeatherMapResponse weatherResponse =
                weatherMapClient.getWeatherData(favoriteCity.getCityName());

        WeatherInfo weatherInfo = WeatherInfo.create(
                favoriteCity.getCityName(),
                weatherResponse.getMain().getTemp(),
                weatherResponse.getMain().getHumidity(),
                weatherResponse.getWind().getSpeed(),
                weatherResponse.getWeather().get(0).getDescription()
        );

        WeatherData weatherData = new WeatherData(weatherInfo, favoriteCity);
        favoriteCity.addWeatherData(weatherData);

        favoriteCityRepository.save(favoriteCity);

        return WeatherDataDTO.fromEntity(weatherData);
    }

    @Transactional
    public List<WeatherDataDTO> getLatestWeatherForUser(String userId) {
        log.info("Getting latest weather data for user: {}", userId);

        return favoriteCityRepository.findByUserId(userId).stream()
                .map(city -> city.getWeatherData().get(city.getWeatherData().size() - 1))
                .map(WeatherDataDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public WeatherDataDTO createWeatherData(WeatherDataDTO dto, String userId) {
        log.info("Creating weather data for city ID: {} and user: {}",
                dto.getFavoriteCityId(), userId);

        FavoriteCity favoriteCity = favoriteCityRepository.findById(dto.getFavoriteCityId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Favorite city not found with ID: " + dto.getFavoriteCityId()));

        // Validar se a cidade pertence ao usuário
        if (!favoriteCity.getUserId().equals(userId)) {
            log.warn("Unauthorized access attempt: User {} trying to access city {} owned by {}",
                    userId, favoriteCity.getCityName(), favoriteCity.getUserId());
            throw new UnauthorizedAccessException(
                    "You don't have permission to access this favorite city");
        }

        // Validar se a cidade do DTO corresponde à cidade favorita
        if (!favoriteCity.getCityName().equals(dto.getInfo().getCityName())) {
            throw new BusinessException(
                    "City name mismatch. Expected: " + favoriteCity.getCityName() +
                            ", but got: " + dto.getInfo().getCityName());
        }

        WeatherData weatherData = dto.toEntity(favoriteCity);
        favoriteCity.addWeatherData(weatherData);

        FavoriteCity saved = favoriteCityRepository.save(favoriteCity);
        WeatherData savedData = saved.getWeatherData()
                .get(saved.getWeatherData().size() - 1);

        log.info("Weather data created successfully for city: {}",
                favoriteCity.getCityName());

        return WeatherDataDTO.fromEntity(savedData);
    }
}
