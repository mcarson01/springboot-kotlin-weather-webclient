package com.example.weather

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

	@Test
	fun `Assert weather results, content and status code`() {
		val entity = restTemplate.getForEntity<String>("/weather")
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains("\"forecast\":\"https://api.weather.gov/gridpoints/TOP/32,81/forecast\"")
		assertThat(entity.body).contains("\"forecastHourly\":\"https://api.weather.gov/gridpoints/TOP/32,81/forecast/hourly\"")
	}

}