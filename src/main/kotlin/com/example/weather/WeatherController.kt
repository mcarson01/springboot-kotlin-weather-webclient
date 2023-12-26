package com.example.weather

import com.fasterxml.jackson.databind.JsonNode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WeatherController() {


	@GetMapping("/weather")
	@ResponseBody
	fun weather(): Mono<Map<String, Any>> {

		val serviceUrl = "https://api.weather.gov/points/39.7456,-97.0892"
		val client = WebClient.create(serviceUrl)

		val response = client.get()
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(JsonNode::class.java)
			.map {response -> response.path("properties")}
			.map {response ->
				mapOf<String, Any>(
					"forecast" to response.path("forecast"),
					"forecastHourly" to response.path("forecastHourly")
				)
			}
			.log()
		/*
			Needed for the future, if the site comes back up.
			"daily":[{
			"day_name": "Monday",
			"temp_high_celsius": 27.2,
			"forecast_blurp": "Partly Sunny"
			}]
			}
		 */

		return response
	}

}
