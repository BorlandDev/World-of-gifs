package com.borlanddev.world_of_gifs

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import viewModels.DetailsViewModel
import viewModels.ListViewModel

class DetailsFragment: Fragment(R.layout.fragment_details) {


    private val detailsViewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    lateinit var buttonDetails: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    buttonDetails = view.findViewById(R.id.button_details)
    }

    override fun onStart() {
        super.onStart()

    buttonDetails.setOnClickListener {

        findNavController().navigate(R.id.action_detailsFragment_to_listFragment)

    }

    }

}