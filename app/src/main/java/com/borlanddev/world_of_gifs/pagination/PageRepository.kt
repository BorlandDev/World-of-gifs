package com.borlanddev.world_of_gifs.pagination

import androidx.paging.PagingData
import com.borlanddev.world_of_gifs.model.Gif
import kotlinx.coroutines.flow.Flow

interface PageRepository {



    // Для симуляции ошибок

  //fun isErrorsEnabled(): Flow<Boolean>


  //  fun serErrorsEnabled(value: Boolean)


    fun getPageGif(): Flow<PagingData<Gif>>
}