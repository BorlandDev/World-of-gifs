package com.borlanddev.world_of_gifs.model

// Десериализуем JSON-array
data class GifResponse(val data: List<Gif>?)


// Десериализуем JSON-objects
data class Gif (val images: Images?)      // UserDbEntity (User с этим классом уже везде работают)

data class Images(val downsized: DownSized?)

data class DownSized(val url: String?)

