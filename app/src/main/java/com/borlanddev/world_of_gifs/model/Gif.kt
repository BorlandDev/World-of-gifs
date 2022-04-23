package com.borlanddev.world_of_gifs.model

import com.google.gson.annotations.SerializedName

data class Gif (

        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
        )

