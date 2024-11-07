package com.guicarneirodev.weatherapi.external.openweathermap;

import com.guicarneirodev.weatherapi.external.openweathermap.data.MainData;
import com.guicarneirodev.weatherapi.external.openweathermap.data.WeatherDescription;
import com.guicarneirodev.weatherapi.external.openweathermap.data.WindData;
import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherMapResponse {
    private MainData main;
    private WindData wind;
    private List<WeatherDescription> weather;
}
