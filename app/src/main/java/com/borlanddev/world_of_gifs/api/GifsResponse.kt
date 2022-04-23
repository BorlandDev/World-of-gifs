package com.borlanddev.world_of_gifs.api

import com.borlanddev.world_of_gifs.model.Gif
import com.google.gson.annotations.SerializedName

class GifsResponse {

    @SerializedName("data")
    lateinit var gifItems: List<Gif>

}