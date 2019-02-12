package ru.prsolution.winstrike.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.prsolution.winstrike.data.repository.CityRepository
import ru.prsolution.winstrike.domain.models.city.City
import ru.prsolution.winstrike.networking.ApiFactory
import ru.prsolution.winstrike.presentation.utils.SingleLiveEvent
import kotlin.coroutines.CoroutineContext

/**
 * Created by Oleg Sitnikov on 2019-02-12
 */



class CityViewModel : ViewModel(){

//    val cityCache = CityCacheDataSourceImpl(Cache())

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val cityRepository : CityRepository = CityRepository(ApiFactory.cityApi)


    val cityList = SingleLiveEvent<List<City>>()

    fun fetchCities(){
        scope.launch {
            val cities = cityRepository.get()
            cityList.postValue(cities)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}