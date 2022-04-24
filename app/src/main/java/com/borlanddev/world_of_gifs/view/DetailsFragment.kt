package com.borlanddev.world_of_gifs.view


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.viewModels.DetailsViewModel
import com.bumptech.glide.Glide

class DetailsFragment: Fragment(R.layout.fragment_details) {


    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gifFullScreen = view.findViewById<ImageView>(R.id.gifFullScreen)

        val gifUrl = arguments?.getSerializable("gifUrl")

        Glide.with(view.context)
            .asGif()
            .load(gifUrl)
            .into(gifFullScreen)


    }



}

