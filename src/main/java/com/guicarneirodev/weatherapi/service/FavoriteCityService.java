package com.guicarneirodev.weatherapi.service;

import com.guicarneirodev.weatherapi.dto.FavoriteCityDTO;
import com.guicarneirodev.weatherapi.exception.ResourceNotFoundException;
import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapClient;
import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapResponse;
import com.guicarneirodev.weatherapi.model.FavoriteCity;
import com.guicarneirodev.weatherapi.model.WeatherData;
import com.guicarneirodev.weatherapi.model.WeatherInfo;
import com.guicarneirodev.weatherapi.repository.FavoriteCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteCityService {
    private final FavoriteCityRepository favoriteCityRepository;
    private final OpenWeatherMapClient weatherClient;

    public FavoriteCityDTO addFavorite(FavoriteCityDTO dto) {
        log.info("Adding favorite city: {}", dto.getCityName());

        weatherClient.validateCityExists(dto.getCityName());

        if (favoriteCityRepository.existsByCityNameAndUserId(
                dto.getCityName(), dto.getUserId())) {
            throw new IllegalArgumentException("City already favorited by user");
        }

        FavoriteCity favoriteCity = dto.toEntity();

        OpenWeatherMapResponse weatherResponse =
                weatherClient.getWeatherData(dto.getCityName());

        WeatherInfo weatherInfo = createWeatherInfo(
                dto.getCityName(), weatherResponse);

        WeatherData weatherData = new WeatherData(weatherInfo, favoriteCity);
        favoriteCity.addWeatherData(weatherData);

        FavoriteCity saved = favoriteCityRepository.save(favoriteCity);
        return FavoriteCityDTO.fromEntity(saved);
    }

    private WeatherInfo createWeatherInfo(String cityName, OpenWeatherMapResponse response) {
        return WeatherInfo.create(
                cityName,
                response.getMain().getTemp(),
                response.getMain().getHumidity(),
                response.getWind().getSpeed(),
                response.getWeather().get(0).getDescription()
        );
    }

    public List<FavoriteCityDTO> getFavoritesByUserId(String userId) {
        log.info("Getting favorites for user: {}", userId);

        return favoriteCityRepository.findByUserId(userId)
                .stream()
                .map(FavoriteCityDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public FavoriteCityDTO getFavorite(Long id) {
        log.info("Getting favorite city by id: {}", id);
        return favoriteCityRepository.findById(id)
                .map(FavoriteCityDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite city not found"));
    }

    public void deleteFavorite(long id) {
        log.info("Deleting favorite city: {}", id);

        if (!favoriteCityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite city not found");
        }
        favoriteCityRepository.deleteById(id);
    }

    public FavoriteCityDTO updateFavorite(Long id, FavoriteCityDTO dto) {
        log.info("Updating favorite city: {}", id);

        FavoriteCity city = favoriteCityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite city not found"));

        city.setCityName(dto.getCityName());

        FavoriteCity updated = favoriteCityRepository.save(city);
        return FavoriteCityDTO.fromEntity(updated);
    }
}
