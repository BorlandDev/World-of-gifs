package com.borlanddev.world_of_gifs.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GifsAPI {

    // Конечная точка запроса
    @GET ("trending")
    fun fetchGifs(
    @Query( "api_key") apiKey: String = "66OOOgYRH6kAMo8oChnvq53TBPhZ9BM0",
    @Query("limit") limit: Int = 25,
    @Query("rating") rating: String = "g"

    ): Call<GiphyResponse>

}