package com.borlanddev.world_of_gifs.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "GifsFetchR"

// Repository pattern

class GifsFetchR {

    private val gifsAPI: GifsAPI

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        gifsAPI = retrofit.create(GifsAPI::class.java)
    }



    /*
    Оборачиваем функцию API Retrofit. Благодаря использованию LiveData, мы можем наблюдать его из
        ListFragment, что бы получить результаты веб-запроса.
            */

    fun fetchGifs(): LiveData<String> {
        val responseLiveData: MutableLiveData<String> = MutableLiveData()
        val gifsRequest: Call<String> = gifsAPI.fetchGifs()

        // функция enqueue выполняет веб-запрос находящийся в обьектке Call, в фоновом потоке
        gifsRequest.enqueue(object : Callback<String> {

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG,"Failed to fetch gifs", t)
            }

            // вернет тип указанный в интерфейсе GifsAPI
            override fun onResponse (
                call: Call<String>,
                response: Response<String>
            ) {
                    Log.d(TAG, "Response received")
                    responseLiveData.value = response.body()
              }
            })

        return responseLiveData
    }



}