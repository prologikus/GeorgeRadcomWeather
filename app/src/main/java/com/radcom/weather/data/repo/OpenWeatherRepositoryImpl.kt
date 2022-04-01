package com.radcom.weather.data.repo

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.radcom.weather.data.genericRepo.WeatherRepository
import com.radcom.weather.data.objects.OpenWeatherDeserializer
import com.radcom.weather.data.uiObjects.CityWeatherModel
import com.radcom.weather.roundTo

class OpenWeatherRepositoryImpl : WeatherRepository {

    override suspend fun getCitiesWeather(): List<CityWeatherModel> {

        //get response from hardcoded URI
        val response =
            Fuel.get("https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22")
                .awaitObjectResponse(OpenWeatherDeserializer()) //json deserializer using GSON

        //convert json object to UI object inline
        return response.third.list.map {
            CityWeatherModel(
                name = it.name,
                coordinates = it.coord.lat to it.coord.lon,
                temperature = it.main.temp.roundTo(1), //round to 1 decimal
                maxTemperature = it.main.temp_max.roundTo(1),
                minTemperature = it.main.temp_min.roundTo(1),
                windSpeed = it.wind.speed,
            )
        }


    }



}