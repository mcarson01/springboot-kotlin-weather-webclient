package com.example.weather.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration


@Configuration
class WeatherConfig {

	@Value("\${weather.external.url}")
	lateinit var weatherUrl: String

	@Value("\${weather.external.timeout}")
	var weatherTimeout: Long = 5

	@Bean
	fun webClient(): WebClient? {
		val httpClient: HttpClient = HttpClient.create()
			.responseTimeout(Duration.ofSeconds(weatherTimeout))
		return WebClient.builder()
			.clientConnector(ReactorClientHttpConnector(httpClient))
			.baseUrl(weatherUrl).build()
	}
}

