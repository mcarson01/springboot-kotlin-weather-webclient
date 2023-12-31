package com.example.weather.controller

import com.example.weather.service.dto.DailyDto
import com.example.weather.service.WeatherService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class WeatherController()  {

	@Autowired
	private lateinit var weatherService: WeatherService

	@GetMapping("/weather")
	@ResponseBody
	fun weather(): Mono<DailyDto> {
		return weatherService.getWeather()
	}

}


