package com.borlanddev.world_of_gifs

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import viewModels.ListViewModel

class ListFragment: Fragment(R.layout.fragmenr_list) {

    private val listViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this).get(ListViewModel::class.java)
    }



    lateinit var buttonList: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     buttonList = view.findViewById(R.id.button_list)


    }


    override fun onStart() {
        super.onStart()

        buttonList.setOnClickListener {

            (activity as MainActivity).navController.navigate(R.id.action_listFragment_to_detailsFragment2)
        }
    }



}