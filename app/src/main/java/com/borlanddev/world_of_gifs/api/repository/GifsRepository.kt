package com.borlanddev.world_of_gifs.api.repository

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.borlanddev.world_of_gifs.api.retrofit.RetrofitClient
import com.borlanddev.world_of_gifs.model.Gif
import com.borlanddev.world_of_gifs.model.GifResponse
import com.borlanddev.world_of_gifs.pagination.GifPageLoader
import com.borlanddev.world_of_gifs.pagination.GifPagingSource
import com.borlanddev.world_of_gifs.pagination.PageRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifsRepository(
    private val ioDispatcher: CoroutineDispatcher
) : PageRepository {


    private val enableErrorsFlow = MutableStateFlow(false)

    override fun isErrorsEnabled(): Flow<Boolean> = enableErrorsFlow

    override fun serErrorsEnabled(value: Boolean) {
        enableErrorsFlow.value = value
    }






    override fun getPageGif(): Flow<PagingData<Gif>> {

        val loader: GifPageLoader = { pageIndex, pageSize ->
            getGif(pageIndex, pageSize)
        }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),

            pagingSourceFactory = { GifPagingSource(loader, PAGE_SIZE) }
        ).flow
    }


    private suspend fun getGif(pageIndex: Int, pageSize: Int): List<Gif> =
        withContext(ioDispatcher) {

            delay(2000)

            if (enableErrorsFlow.value) throw IllegalStateException("Error!")

            val offset = pageIndex * pageSize

            // get page
            val list = RetrofitClient.gifsAPI.fetchGifs(offset, pageSize)


            return@withContext list
                .map(::)
        }










    fun getGifs(
        apikey: String,
        onSuccess: (List<Gif>) -> Unit,
        onFailure: (msg: String) -> Unit,
        pageSize: Int = 25,
        pageIndex: Int = 3
    ) {


        // Call.enqueue выполняет веб-запрос в фоновом потоке.
        // Обьект Callback позволяет определить что мы хотим сделать после получения ответа на запрос.

        RetrofitClient.gifsAPI.fetchGifs(apikey, pageSize).enqueue(object : Callback<GifResponse> {

            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {

                if (response.code() == 200) {
                    // в случае успешного ответа от сервера , получаем данные если они есть
                    onSuccess(response.body()?.data ?: listOf())
                }
            }

            override fun onFailure(call: Call<GifResponse>, t: Throwable) {
                onFailure(t.localizedMessage ?: "")
            }
        })
    }


    private companion object {
        const val PAGE_SIZE = 20
    }

}


