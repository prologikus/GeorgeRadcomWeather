package com.radcom.weather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radcom.weather.data.RepositoryManager
import com.radcom.weather.data.uiObjects.CityWeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val citiesList: MutableLiveData<List<CityWeatherModel>?> = MutableLiveData(null)

    val loading = MutableLiveData(false)

    //a error handle would be nice too

    init {
        //when viewModel is initialised, get cities
        getCities()
    }

    private fun getCities() {
        //without blocking the main thread
        viewModelScope.launch(Dispatchers.IO) {
            //mark loading
            loading.postValue(true)
            try {
                citiesList.postValue(RepositoryManager.weatherRepository.getCitiesWeather())
            } catch (e: Exception) {
                //handle errors
            }
            //regardless of the result stop loading
            loading.postValue(false)
        }
    }

    fun refreshPressed() {
        getCities()
    }

}