package com.example.weather.integration

import com.example.weather.integration.domain.ForecastDomain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WeatherIntegration {

    @Autowired
    private lateinit var webClient: WebClient

    fun getWeather(): Mono<ForecastDomain> {
        return webClient.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ForecastDomain::class.java)
    }


}