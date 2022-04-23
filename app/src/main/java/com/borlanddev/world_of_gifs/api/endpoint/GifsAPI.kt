package com.borlanddev.world_of_gifs.api.endpoint


import com.borlanddev.world_of_gifs.model.GifResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsAPI {

    // Конечная точка запроса
    @GET ("trending")
    fun fetchGifs(
    @Query( "api_key") apiKey: String,
    @Query("limit") limit: Int = 25,
    @Query("rating") rating: String = "g"

    ): Call<GifResponse>

}