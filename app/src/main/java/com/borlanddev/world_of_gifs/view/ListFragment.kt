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
import com.borlanddev.world_of_gifs.api.GifsAPI
import com.borlanddev.world_of_gifs.api.GifsFetchR
import com.borlanddev.world_of_gifs.viewModels.ListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "ListFragment"

class ListFragment: Fragment(R.layout.fragmenr_list) {

    private lateinit var gifsRecyclerView: RecyclerView

    private val listViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Генириуем обьект представляющий собой исполняемый веб-запрос
        val gifsLiveData: LiveData<String> = GifsFetchR().fetchGifs()

        gifsLiveData.observe(
            this,
            Observer { responseString ->
                Log.d(TAG, "Response received: $responseString")
            }
        )


    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gifsRecyclerView = view.findViewById(R.id.recycler_view)
        gifsRecyclerView.layoutManager = GridLayoutManager(context, 3)

    }


}