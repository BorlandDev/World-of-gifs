package com.borlanddev.world_of_gifs.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.api.GifsFetchRepo
import com.borlanddev.world_of_gifs.model.Gif
import com.borlanddev.world_of_gifs.viewModels.ListViewModel

private const val TAG = "ListFragment"

class ListFragment: Fragment(R.layout.fragmenr_list) {

    private lateinit var gifsRecyclerView: RecyclerView

    private val listViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Генириуем обьект представляющий собой исполняемый веб-запрос
        val gifsLiveData: LiveData<List<Gif>> = GifsFetchRepo().fetchGifs(R.string.api_key)

        gifsLiveData.observe(
            this,
            Observer { gifItems ->
                Log.d(TAG, "Response received: $gifItems")
            }
        )


    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gifsRecyclerView = view.findViewById(R.id.recycler_view)
        gifsRecyclerView.layoutManager = GridLayoutManager(context, 3)

    }


}