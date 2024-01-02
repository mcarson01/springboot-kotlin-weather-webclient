package com.example.weather.integration

import com.example.weather.data.Daily
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WeatherIntegration {

    fun getWeather(): Mono<Daily> {
        val serviceUrl = "https://api.weather.gov/gridpoints/MLB/33,70/forecast"
        val client = WebClient.create(serviceUrl)

        val response = client.get()
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map {response -> response.path("properties")}
            .map {response ->
                val periodMap = mapOf<String, Any>(
                    "day_name" to response.path("periods").get(0).path("name"),
                    "temp_high_celsius" to (response.path("periods").get(0).path("temperature").asInt() - 32) * 5 / 9,
                    "forecast_blurp" to response.path("periods").get(0).path("shortForecast"),
                )
                Daily(listOf(periodMap))
            }
        return response
    }

}