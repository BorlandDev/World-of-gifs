package com.borlanddev.world_of_gifs.api.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
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

private const val TAG = "GifsRepository"


class GifsRepository(
    private val ioDispatcher: CoroutineDispatcher
) : PageRepository {

    // Для симуляции ошибок


    //  private val enableErrorsFlow = MutableStateFlow(false)

//    override fun isErrorsEnabled(): Flow<Boolean> = enableErrorsFlow
//
//    override fun serErrorsEnabled(value: Boolean) {
//        enableErrorsFlow.value = value
//    }




    // Конфигурируем пэйджер. Логика работы пагинации. Так же мы будем в дальнейшем прослушивать Flow.
    override fun getPageGif(): Flow<PagingData<Gif>> {

        // Создаем загрузчик
        val loader: GifPageLoader = { pageIndex, pageSize ->
            getGif(pageIndex = 4, pageSize = 60, apikey = "")
        }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),

            // Принимает фабрику и создает ее в случае невалидности текущего источника данных.
            pagingSourceFactory = { GifPagingSource(loader, PAGE_SIZE) }
        ).flow
    }


    private suspend fun getGif( apikey: String,
        pageIndex: Int, pageSize: Int): MutableLiveData<List<Gif>> =

        withContext(ioDispatcher) {

            delay(2000)

          //  if (enableErrorsFlow.value) throw IllegalStateException("Error!")

            val gifsListLiveData = MutableLiveData<List<Gif>>(listOf())

            val list = RetrofitClient.gifsAPI.fetchGifs()
                    .enqueue(object : Callback<GifResponse> {

                override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {



                    if (response.code() == 200) {
                        // в случае успешного ответа от сервера , получаем данные если они есть
                        val giphyResponse  = (response.body()?.data ?: listOf())
                        val itemsGif: List<Gif> = giphyResponse

                        return gifsListLiveData.value
                    }
                }


                override fun onFailure(call: Call<GifResponse>, t: Throwable) {
                    Log.d(TAG, "FAILURE LOAD ${t.localizedMessage} ?: $t")
                }


                    })



            return@withContext list
                .map(Call<Response>::MutableLiveData<List<Gif>>)
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

        RetrofitClient.gifsAPI.fetchGifs(apikey, pageSize, pageIndex).enqueue(object : Callback<GifResponse> {

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


