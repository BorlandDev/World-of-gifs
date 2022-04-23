package com.borlanddev.world_of_gifs.model

import com.google.gson.annotations.SerializedName

data class Gif (

        @SerializedName("title") val title: String?,
        @SerializedName("url") val url: String?
 )


// Храним список гифок полученый от сервера
data class GifResponse(
        val data: List<Gif>?
)