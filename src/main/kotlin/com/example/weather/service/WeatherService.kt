package com.example.weather.service

import com.example.weather.dto.ForecastDto
import com.example.weather.integration.WeatherIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherService {

    @Autowired
    private lateinit var weatherIntegration: WeatherIntegration

    fun getWeather(): Mono<ForecastDto> {
        return weatherIntegration.getWeather().map { response -> ForecastDto(response.forecast) }
    }
}