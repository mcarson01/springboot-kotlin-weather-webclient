package com.example.weather.service

import com.example.weather.data.Forecast
import com.example.weather.data.ForecastResponse
import com.example.weather.integration.WeatherIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherService {

    @Autowired
    private lateinit var weatherIntegration: WeatherIntegration

    fun getWeather(): Mono<ForecastResponse> {
        return weatherIntegration.getWeather().map { response -> ForecastResponse(response.forecast) }
    }
}