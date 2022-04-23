package com.borlanddev.world_of_gifs.api.retrofit

import com.borlanddev.world_of_gifs.api.endpoint.GifsAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

    // Retrofit предаставляет реализацию интерфейса
    val gifsAPI: GifsAPI
        get() = getClient().create(GifsAPI::class.java)



    // Инициализация и настройка Retrofit
    private fun getClient(): Retrofit {
        if (retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build()

            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
            return retrofit!!
    }
}