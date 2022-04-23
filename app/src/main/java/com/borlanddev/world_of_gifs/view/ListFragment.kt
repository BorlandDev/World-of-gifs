package com.borlanddev.world_of_gifs.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.viewModels.ListViewModel

private const val TAG = "ListFragment"

class ListFragment: Fragment(R.layout.fragmenr_list) {

    private lateinit var gifsRecyclerView: RecyclerView

    private val listViewModel by viewModels<ListViewModel>()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gifsRecyclerView = view.findViewById(R.id.recycler_view)
        // Конфигурируем recycler отображать данные в виде таблицы
        gifsRecyclerView.layoutManager = GridLayoutManager(context, 3)


        // Наблюдаем за данными
        listViewModel.gifsList.observe(

            viewLifecycleOwner
        ) {


            // Временно выводим ответ сервера в лог
            it.forEachIndexed { index, gif ->
                Log.d(TAG, "ITEM $index ${gif.title}")


            }
        }


        // Выполняем запрос на сервер
        listViewModel.getGifs(resources.getString(R.string.api_key))
    }



}


