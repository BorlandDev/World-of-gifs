package com.borlanddev.world_of_gifs.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.borlanddev.world_of_gifs.model.Gif
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Repository"
// Repository pattern

class GifsFetchRepo {

    private val gifsAPI: GifsAPI

    init {  // Инициализация Retrofit
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        // Retrofit предаставляет реализацию интерфейса
        gifsAPI = retrofit.create(GifsAPI::class.java)
    }



    /*
    Оборачиваем функцию API Retrofit. Благодаря использованию LiveData, мы можем наблюдать его из
        ListFragment, что бы получить результаты веб-запроса.
            */

    // Отправка запроса
    fun fetchGifs(apiKey: Int): LiveData<List<Gif>> {

        val responseLiveData: MutableLiveData<List<Gif>> = MutableLiveData()

        val gifsRequest: Call<GiphyResponse> = gifsAPI.fetchGifs(apiKey.toString())


        // функция enqueue выполняет веб-запрос находящийся в обьектке Call, в фоновом потоке
        gifsRequest.enqueue(object : Callback<GiphyResponse> {

            override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
                Log.e(TAG,"Failed to fetch gifs", t)
            }

            // вернет тип указанный в интерфейсе GifsAPI
            override fun onResponse (
                call: Call<GiphyResponse>,
                response: Response<GiphyResponse>
            ) {
                Log.d(TAG, "Response received")

                val giphyResponse: GiphyResponse? = response.body()
                val gifsResponse: GifsResponse? = giphyResponse?.gifs
                var gifItems: List<Gif> = gifsResponse?.gifItems ?: mutableListOf()
                gifItems = gifItems.filterNot {
                    it.url.isBlank() }

                responseLiveData.value = gifItems
            }
        })

        return responseLiveData
    }






}