package com.example.weather.integration

import com.example.weather.data.Forecast
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WeatherIntegration {

    @Value("\${weather.url}")
    lateinit var weatherUrl: String

    fun getWeather(): Mono<Forecast> {
        val client = WebClient.create(weatherUrl)

        val response = client.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map {response -> response.path("properties")}
            .map {response ->
                val periodMap = mutableMapOf<String, Any>(
                    "day_name" to response.path("periods").get(0).path("name"),
                    "temp_high_celsius" to (response.path("periods").get(0).path("temperature").asInt() - 32) * 5 / 9,
                    "forecast_blurp" to response.path("periods").get(0).path("shortForecast"),
                )
                Forecast(mutableListOf(periodMap))
            }
        return response
    }

}