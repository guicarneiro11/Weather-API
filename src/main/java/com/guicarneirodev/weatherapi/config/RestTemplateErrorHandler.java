package com.guicarneirodev.weatherapi.config;

import com.guicarneirodev.weatherapi.exception.BusinessException;
import com.guicarneirodev.weatherapi.exception.WeatherServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() ||
                response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            log.error("Server error: {} {}", response.getStatusCode(), response.getStatusText());
            throw new WeatherServiceException("Error communicating with weather service");
        } else if (response.getStatusCode().is4xxClientError()) {
            log.error("Client error: {} {}", response.getStatusCode(), response.getStatusText());
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new BusinessException("Resource not found");
            }
            throw new BusinessException("Invalid request to weather service");
        }
    }
}
