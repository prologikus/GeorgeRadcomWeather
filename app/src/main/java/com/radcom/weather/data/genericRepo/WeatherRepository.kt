package com.radcom.weather.data.genericRepo
import com.radcom.weather.data.uiObjects.CityWeatherModel

interface WeatherRepository {

    /**
     * Returns a list of cities with weather information.
     * THROWS EXCEPTIONS!
     */
    suspend fun getCitiesWeather():List<CityWeatherModel>

}