package com.guicarneirodev.weatherapi.external.openweathermap;

import com.guicarneirodev.weatherapi.exception.BusinessException;
import com.guicarneirodev.weatherapi.exception.WeatherServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherMapClient {
    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    public OpenWeatherMapResponse getWeatherData(String cityName) {
        String url = buildUrl(cityName);

        try {
            log.debug("Fetching weather data for city: {}", cityName);
            ResponseEntity<OpenWeatherMapResponse> response =
                    restTemplate.getForEntity(url, OpenWeatherMapResponse.class);

            if (response.getBody() == null) {
                throw new WeatherServiceException("No weather data received from API");
            }

            return response.getBody();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BusinessException("City not found: " + cityName);
            }
            throw new WeatherServiceException("Error fetching weather data", e);
        } catch (RestClientException e) {
            throw new WeatherServiceException("Error communicating with weather service", e);
        }
    }

    public void validateCityExists(String cityName) {
        String url = buildUrl(cityName);

        try {
            restTemplate.getForEntity(url, OpenWeatherMapResponse.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BusinessException("City not found: " + cityName);
            }
            throw new WeatherServiceException("Error validating city", e);
        }
    }

    private String buildUrl(String cityName) {
        return String.format("%s/weather?q=%s&appid=%s&units=metric",
                apiUrl, cityName, apiKey);
    }
}