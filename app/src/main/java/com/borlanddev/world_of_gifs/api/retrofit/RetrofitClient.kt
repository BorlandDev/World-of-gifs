package com.borlanddev.world_of_gifs.api.retrofit


import com.borlanddev.world_of_gifs.api.endpoint.GifsAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.giphy.com/v1/gifs/"

    // Retrofit предаставляет реализацию интерфейса
    val gifsAPI: GifsAPI
        get() = getClient().create(GifsAPI::class.java)



    // Инициализация и настройка Retrofit
    private fun getClient(): Retrofit {
        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}