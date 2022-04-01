package com.radcom.weather.data

import com.radcom.weather.data.genericRepo.WeatherRepository
import com.radcom.weather.data.repo.FakeWeatherRepositoryImpl
import com.radcom.weather.data.repo.OpenWeatherRepositoryImpl

object RepositoryManager {

    //here goes all our repositories
    val weatherRepository: WeatherRepository by lazy {
        OpenWeatherRepositoryImpl()
    }
}
