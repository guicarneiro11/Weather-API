package com.guicarneirodev.weatherapi;

import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapClient;
import com.guicarneirodev.weatherapi.external.openweathermap.OpenWeatherMapResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OpenWeatherMapClientTest {

    @Autowired
    private OpenWeatherMapClient weatherClient;

    @Test
    void shouldFetchWeatherData() {
        // Dado uma cidade válida
        String cityName = "London";

        // Quando buscar os dados
        OpenWeatherMapResponse response = weatherClient.getWeatherData(cityName);

        // Então
        assertNotNull(response);
        assertNotNull(response.getMain());
        assertNotNull(response.getMain().getTemp());
        assertNotNull(response.getWeather());
        assertFalse(response.getWeather().isEmpty());
    }
}
