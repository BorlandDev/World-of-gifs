package com.borlanddev.world_of_gifs.view


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.viewModels.DetailsViewModel
import com.bumptech.glide.Glide

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val detailsViewModel by viewModels<DetailsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gifFullScreen = view.findViewById<ImageView>(R.id.gifFullScreen)

        // Получаем url из аргументов фрагмента
        val gifUrl = arguments?.getSerializable("gifUrl")

        // Отображаем гифку по выбранную пользователем
        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .placeholder(R.drawable.bill_up_close)
            .into(gifFullScreen)
    }


}
