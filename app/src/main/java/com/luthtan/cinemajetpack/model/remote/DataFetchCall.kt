package com.luthtan.cinemajetpack.model.remote

import androidx.lifecycle.MutableLiveData
import com.luthtan.cinemajetpack.util.EspressIdlingResources
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class DataFetchCall<ResultType>(
    private val responseLiveData: MutableLiveData<ResultType>,
    private val responseErrorData: MutableLiveData<String>
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
                        responseLiveData.postValue(request.body())
                    responseErrorData.postValue(null)
                } else if (request.code() == 401) {
                    responseLiveData.postValue(null)
                } else {
                    responseErrorData.postValue(request.message())
                }
                EspressIdlingResources.decrement()
            } catch (exception: Exception) {
                exception.printStackTrace()
                responseErrorData.postValue(exception.toString())
            }
        }
    }
}