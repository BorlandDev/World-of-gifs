package com.borlanddev.world_of_gifs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.databinding.FragmentListBinding
import com.borlanddev.world_of_gifs.model.Gif
import com.borlanddev.world_of_gifs.viewModels.ListViewModel
import com.bumptech.glide.Glide


class ListFragment: Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val listViewModel by viewModels<ListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)

        // Конфигурируем recycler отображать данные в виде таблицы
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)


        // Наблюдаем за данными
        listViewModel.gifListLivedata.observe(
            viewLifecycleOwner
        ) {

            // Обновляем данные в ресайклере
            binding.recyclerView.adapter = GifAdapter(it)
            }
        }



    // Холдер - ячейка (визуальный элемент списка, контейнер для наших данных)
    private class GifHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        private lateinit var gif: Gif
        private val gifView = itemView.findViewById<ImageView>(R.id.gifImageView)

        init { // ставим слушателя на каждую вьюшку вьюХолдера
            itemView.setOnClickListener(this)
        }

        // связываем данные с вью
        fun bind(gif: Gif) {
            this.gif = gif

            val url = gif.images?.downsized?.url ?: ""


            // загрузка гифки в нашу вьюшку
            Glide.with(itemView.context) // FragmentActivity тоже можно передать
                .asGif()
                .placeholder(R.drawable.bill_up_close)
                .load(url)
                .onlyRetrieveFromCache(false)
                .into(gifView)

        }


        override fun onClick(v: View?) {

            // кладем информацию о выбранной гифке
        //    val bundle = Bundle()
          //  bundle.putSerializable("gifUrl", gif.images?.downsized?.url ?: "")

            // Передаем в аргументах выбранную гифку
            v?.findNavController()?.navigate(
                R.id.action_listFragment_to_detailsFragment,
                bundleOf("gifUrl" to (gif.images?.downsized?.url ?: "")),
                    navOptions {
                        anim {
                            enter = R.anim.enter
                            exit = R.anim.exit
                            popEnter = R.anim.pop_enter
                            popExit = R.anim.pop_exit
                        }
                    })

        }
    }


    private inner class GifAdapter(private val gifs: List<Gif>) :
        RecyclerView.Adapter<GifHolder>() {

        // функция отвечает за создание вьюХолдера на дисплее
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {

            // передали макет для каждой однотипной вью в ресайклере
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false) as ImageView

            return GifHolder(view)
        }

        // recycler узнает заранее сколько элементов ему нужно будет отобразить
        override fun getItemCount(): Int = gifs.size

        override fun onBindViewHolder(holder: GifHolder, position: Int) {

            val gif = gifs[position]

            // передаем информацию о выбранной гифке по ее позиции
            holder.bind(gif)
        }
    }


}


