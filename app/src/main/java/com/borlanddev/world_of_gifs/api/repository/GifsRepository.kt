package com.borlanddev.world_of_gifs.api.repository

import com.borlanddev.world_of_gifs.api.retrofit.RetrofitClient
import com.borlanddev.world_of_gifs.model.Gif
import com.borlanddev.world_of_gifs.model.GifResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object GifsRepository {

    fun getGifs(
        apikey: String,
        onSuccess: (List<Gif>) -> Unit,
        onFailure: (msg: String) -> Unit,
        limit: Int = 25
    ) {


     // Call.enqueue выполняет веб-запрос в фоновом потоке.
     // Обьект Callback позволяет определить что мы хотим сделать после получения ответа на запрос.

        RetrofitClient.gifsAPI.fetchGifs(apikey, limit).enqueue(object: Callback<GifResponse> {

            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {

                if(response.code() == 200) {
                    // в случае успешного ответа от сервера , получаем данные если они есть
                    onSuccess(response.body()?.data ?: listOf())
                }
            }

            override fun onFailure(call: Call<GifResponse>, t: Throwable) {
                onFailure(t.localizedMessage ?: "")
            }
        })
    }
}


