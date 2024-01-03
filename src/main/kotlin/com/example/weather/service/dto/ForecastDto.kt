package com.example.weather.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DailyDto (@JsonProperty("daily") val dailyList: List<DailyItems>)

data class DailyItems(
    @JsonProperty("day_name")
    val dayName: String,
    @JsonProperty("temp_high_celsius")
    val tempHighCelsius: Int,
    @JsonProperty("forecast_blurp")
    val forecastBlurb: String
)