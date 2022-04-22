package com.borlanddev.world_of_gifs.api

import retrofit2.Call
import retrofit2.http.GET

interface GifsAPI {

    https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL
            // &q=&limit=25&offset=0&rating=g&lang=en


    @GET("v1/gifs/?method=trending" +
            "&api_key=66OOOgYRH6kAMo8oChnvq53TBPhZ9BM0" +
                    "&q=" +
                    "&limit=25"  +
                    "&offset=0" +
                    "&rating=g" +
                    "&lang=en"
            )


    fun fetchGifs(): Call<String>

}