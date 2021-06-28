package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import com.luthtan.cinemajetpack.vo.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class DataFetchCall<ResultType>(
    private val responseLiveData: MutableLiveData<Resource<ResultType>>,
) {

    abstract suspend fun createCallAsync(): Response<ResultType>

    fun execute() {
        callNetworkData()
    }

    private fun callNetworkData() {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            try {
                val request = createCallAsync()
                if (request.isSuccessful) {
                    if (request.body() != null)
                        responseLiveData.postValue(Resource.success(request.body()!!))
                } else {
                    responseLiveData.postValue(Resource.error(request.message(), request.body()!!))
                }
                EspressIdlingResources.decrement()
            } catch (exception: java.lang.Exception) {
                exception.printStackTrace()
                responseLiveData.postValue(Resource.error(exception.toString(), null))
                EspressIdlingResources.decrement()
            }
        }
    }
}