package com.example.weather

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@RestController
class WeatherController()  {


	@GetMapping("/weather")
	@ResponseBody
	fun weather(): Mono<Daily> {

		val serviceUrl = "https://api.weather.gov/gridpoints/MLB/33,70/forecast"
		val client = WebClient.create(serviceUrl)

		val response = client.get()
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(JsonNode::class.java)
			.map {response -> response.path("properties")}
			.log("INFO")
			.map {response ->
				val periodMap = mapOf<String, Any>(
					"day_name" to response.path("periods").get(0).path("name"),
					"temp_high_celsius" to (response.path("periods").get(0).path("temperature").asInt() - 32) * 5 / 9,
					"forecast_blurp" to response.path("periods").get(0).path("shortForecast"),
				)
				Daily(listOf(periodMap))
			}
			.log()
		return response
	}

}


class Daily(val daily: List<Map<String, Any>>)  {}