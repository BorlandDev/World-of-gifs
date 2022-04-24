package com.borlanddev.world_of_gifs.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.api.repository.GifsRepository
import com.borlanddev.world_of_gifs.model.Gif

private const val TAG = "ListViewModel"

class ListViewModel: ViewModel() {

    val gifsListLiveData = MutableLiveData<List<Gif>>(listOf())

    init {

    // Выполняем запрос на сервер

        // Предоставляем реализацию поведения функций в UI-потоке, после ответа на запрос (в фоновом)
        GifsRepository.getGifs("66OOOgYRH6kAMo8oChnvq53TBPhZ9BM0", {
            gifsListLiveData.value =
                it    // Если ответ успешный, полученные данные кладем в LiveData
        }, {
            Log.d(TAG, "FAILURE LOAD $it")
        }, limit = 25)
    }



}




/*
 // Выполняем запрос на сервер
    fun getGifs(apiKey: String, limit: Int = 25) {

        // Предоставляем реализацию поведения функций в UI-потоке, после ответа на запрос (в фоновом)
        GifsRepository.getGifs(apiKey, {
            gifsListLiveData.value =
                it    // Если ответ успешный, полученные данные кладем в LiveData
        }, {
            Log.d(TAG, "FAILURE LOAD $it")
        }, limit)
    }


 */