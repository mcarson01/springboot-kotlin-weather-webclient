package com.example.weather.integration.domain

data class ForecastDomain  (
    val properties: Periods
)

data class Periods  (
    val periods: List<Period>
)

data class Period  (
    val name: String,
    val temperature: Int,
    val shortForecast: String
)

