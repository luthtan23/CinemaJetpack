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
    open fun saveResult(result: ResultType) {}
    open fun shouldFetchFromDB(): Boolean = false
    open fun loadFromDB(): ResultType? = null

    fun execute() {
        responseLiveData.postValue(Resource.loading())
        if (shouldFetchFromDB()) {
            callLoadFromDB()
        } else {
            callNetworkData()
        }
    }

    private fun callNetworkData() {
        EspressIdlingResources.increment()
        GlobalScope.launch {
            try {
                val request = createCallAsync()
                if (request.isSuccessful) {
                    if (request.body() != null)
                        saveResult(request.body()!!)
                        responseLiveData.postValue(Resource.success(request.body()!!))
                } else {
                    responseLiveData.postValue(Resource.error(request.message(), request.body()!!))
                }
                EspressIdlingResources.decrement()
            } catch (exception: java.lang.Exception) {
                exception.printStackTrace()
                responseLiveData.postValue(Resource.error(exception.toString(), null))
            }
        }
    }

    private fun callLoadFromDB() {
        GlobalScope.launch {
            try {
                val response = loadFromDB()
                if (response != null) {
                    saveResult(response)
                    responseLiveData.postValue(Resource.success(response))
                } else
                    responseLiveData.postValue(Resource.error("Not Found", null))
            } catch (exception: Exception) {
                responseLiveData.postValue(Resource.error(exception.toString(), null))
            }
        }
    }
}