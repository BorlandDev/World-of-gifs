package com.borlanddev.world_of_gifs.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.viewModels.DetailsViewModel

class DetailsFragment: Fragment(R.layout.fragment_details) {


    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



    override fun onStart() {
        super.onStart()


      //  findNavController().navigate(R.id.action_detailsFragment_to_listFragment)



    }

}