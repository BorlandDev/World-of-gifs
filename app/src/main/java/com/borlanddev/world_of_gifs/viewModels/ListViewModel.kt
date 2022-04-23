package com.borlanddev.world_of_gifs.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.world_of_gifs.api.repository.GifsRepository
import com.borlanddev.world_of_gifs.model.Gif

private const val TAG = "ListViewModel"

class ListViewModel: ViewModel() {

    val gifsList = MutableLiveData<List<Gif>>(listOf())

    // Выполняем запрос на сервер
    fun getGifs(apiKey: String, limit: Int = 25) {

       // Предоставляем реализацию поведения функций в UI-потоке, после ответа на запрос (в фоновом)
            GifsRepository.getGifs( apiKey, {
                gifsList.value = it    // Если ответ успешный, полученные данные кладем в LiveData
            }, {
                Log.d(TAG, "FAILURE LOAD $it")
            }, limit)
    }

}