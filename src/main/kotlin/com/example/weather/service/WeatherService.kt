package com.example.weather.service

import com.example.weather.service.dto.DailyItems
import com.example.weather.service.dto.DailyDto
import com.example.weather.integration.WeatherIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class WeatherService {

    @Autowired
    private lateinit var weatherIntegration: WeatherIntegration

    fun getWeather(): Mono<DailyDto> {
        return weatherIntegration.getWeather().map { response -> DailyDto(
            listOf(
                DailyItems(
                    response.properties.periods[0].name,
                    (response.properties.periods[0].temperature - 32) * 5 / 9,
                    response.properties.periods[0].shortForecast
                )
            ))
        }
    }
}