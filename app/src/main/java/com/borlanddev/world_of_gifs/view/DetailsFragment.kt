package com.borlanddev.world_of_gifs.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.databinding.FragmentDetailsBinding
import com.borlanddev.world_of_gifs.viewModels.DetailsViewModel
import com.bumptech.glide.Glide

class DetailsFragment: Fragment(R.layout.fragment_details) {

    lateinit var binding: FragmentDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация биндинга
        binding = FragmentDetailsBinding.bind(view)

        // Получаем url из аргументов фрагмента
        val gifUrl = arguments?.getSerializable("gifUrl")

        // Отображаем гифку по выбранную пользователем
        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .placeholder(R.drawable.bill_up_close)
            .into(binding.gifFullScreen)
    }


}
