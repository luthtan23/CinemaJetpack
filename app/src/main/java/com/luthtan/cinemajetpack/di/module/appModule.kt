package com.luthtan.cinemajetpack.di.module

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.luthtan.cinemajetpack.MyApplication
import com.luthtan.cinemajetpack.model.local.preference.PreferenceConstants
import com.luthtan.cinemajetpack.model.local.preference.PreferenceHelper
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.model.remote.ApiService
import com.luthtan.cinemajetpack.util.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single<Gson> {
        val builder =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        builder.setLenient().create()
    }

    single {
        var loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(loggingInterceptor)
        httpClient.connectTimeout(ApiConstant.API_TIME_OUT, TimeUnit.MILLISECONDS)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        var okHttpClient = httpClient.build()

        Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(get() as Gson))
            .build()
    }

    single {
        (get<Retrofit>()).create(ApiService::class.java)
    }

    single {
        PreferenceHelper(
            (androidApplication() as MyApplication).getSharedPreferences(
                PreferenceConstants.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        )
    }

    single { Utils }

}