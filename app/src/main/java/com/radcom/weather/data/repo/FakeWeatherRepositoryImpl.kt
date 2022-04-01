package com.radcom.weather.data.repo

import com.radcom.weather.data.genericRepo.WeatherRepository
import com.radcom.weather.data.uiObjects.CityWeatherModel
import kotlinx.coroutines.delay


//USED FOR TESTING

class FakeWeatherRepositoryImpl : WeatherRepository {

    override suspend fun getCitiesWeather(): List<CityWeatherModel> {
        val cityFake = CityWeatherModel(
            name = "Bucuresti",
            coordinates = 22.0 to 22.4,
            temperature = 22.2,
            maxTemperature = 29.2,
            minTemperature = 15.5,
            windSpeed = 10.0,
        )

        //fake delay before return
        delay(3000)
        return listOf(cityFake, cityFake)
    }

}