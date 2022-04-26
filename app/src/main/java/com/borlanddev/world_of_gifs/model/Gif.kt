package com.borlanddev.world_of_gifs.model

// Десериализуем JSON-array
data class GifResponse(val data: List<Gif>?)


// Десериализуем JSON-objects
data class Gif (val images: Images?)

data class Images(val downsized: DownSized?)

data class DownSized(val url: String?)

sds