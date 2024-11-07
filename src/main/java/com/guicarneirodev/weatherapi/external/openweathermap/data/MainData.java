package com.guicarneirodev.weatherapi.external.openweathermap.data;

import lombok.Data;

@Data
public class MainData {
    private Double temp;
    private Integer humidity;
    private Double feels_like;
    private Integer pressure;
}
