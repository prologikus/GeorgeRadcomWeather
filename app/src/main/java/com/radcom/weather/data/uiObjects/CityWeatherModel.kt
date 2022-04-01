package com.radcom.weather.data.uiObjects

data class CityWeatherModel(
    var name: String,
    var coordinates: Pair<Double,Double>, //a custom object would be nicer
    var temperature: Double,
    var maxTemperature: Double,
    var minTemperature: Double,
    var windSpeed: Double,
)
