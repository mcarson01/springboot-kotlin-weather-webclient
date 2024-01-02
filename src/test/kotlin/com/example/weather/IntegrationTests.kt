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
		assertThat(entity.body).contains("{\"daily\":[{\"day_name\":\"")
		assertThat(entity.body).contains("\"temp_high_celsius\"")
		assertThat(entity.body).contains("\"forecast_blurp\":")
	}

}