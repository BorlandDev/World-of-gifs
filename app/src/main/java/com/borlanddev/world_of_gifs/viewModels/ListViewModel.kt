package com.borlanddev.world_of_gifs.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.api.repository.GifsRepository
import com.borlanddev.world_of_gifs.model.Gif

private const val TAG = "ListViewModel"

class ListViewModel(application: Application): AndroidViewModel(application) {


     val gifsListLiveData = MutableLiveData<List<Gif>>(listOf())

    init {

        // Выполняем запрос на сервер

        // Предоставляем реализацию поведения функций в UI-потоке, после ответа на запрос (в фоновом)
        GifsRepository.getGifs(application.resources.getString(R.string.api_key), {

            gifsListLiveData.value = it    // Если ответ успешный, полученные данные кладем в LiveData
        }, {
            Log.d(TAG, "FAILURE LOAD $it")
        }, limit = 25)
    }


}

