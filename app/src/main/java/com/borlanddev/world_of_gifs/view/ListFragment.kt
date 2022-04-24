package com.borlanddev.world_of_gifs.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.borlanddev.world_of_gifs.R
import com.borlanddev.world_of_gifs.model.Gif
import com.borlanddev.world_of_gifs.viewModels.ListViewModel
import com.bumptech.glide.Glide

private const val TAG = "ListFragment"

class ListFragment: Fragment(R.layout.fragment_list) {

    private lateinit var gifsRecyclerView: RecyclerView

    private val listViewModel by viewModels<ListViewModel>()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().navigate(R.id.action_listFragment_to_detailsFragment2)


        gifsRecyclerView = view.findViewById(R.id.recycler_view)
        // Конфигурируем recycler отображать данные в виде таблицы
        gifsRecyclerView.layoutManager = GridLayoutManager(context, 3)


        // Наблюдаем за данными
        listViewModel.gifsListLiveData.observe(
            viewLifecycleOwner
        ) {

            // Обновляем данные в ресайклере
            gifsRecyclerView.adapter = GifAdapter(it)

            // Временно выводим ответ сервера в лог
            it.forEachIndexed { index, gif ->
                Log.d(TAG, "ITEM $index ${gif.title}")


            }
        }


    // Выполняем запрос на сервер
  //  listViewModel.getGifs(resources.getString(R.string.api_key))

    }







    private class GifHolder (view: View): RecyclerView.ViewHolder(view),
            View.OnClickListener{

        private lateinit var gif: Gif

        private var gifView = itemView.findViewById<ImageView>(R.id.gifImageView)


        init { // ставим слушателя на каждую вьюшку вьюХолдера
            itemView.setOnClickListener (this)
        }

        fun bind(gif: Gif) {

            val url = gif.url?.get(position)

            Glide.with(itemView.context)
                .asGif()
                .load(url)
                .into(gifView)

        }


        override fun onClick(v: View?) {

            val bundle = Bundle()
            val gifUrl = gif.url

            bundle.putSerializable("gifUrl", gifUrl)


            // Передать в аргументах выбранную гифку
            v?.findNavController()?.navigate(
                R.id.action_listFragment_to_detailsFragment2,
                bundle)
        }
    }


    private class GifAdapter(private val gifs: List<Gif>): RecyclerView.Adapter<GifHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {

            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)


            return GifHolder(view)
        }


        override fun getItemCount(): Int = gifs.size



        override fun onBindViewHolder(holder: GifHolder, position: Int) {

        val gif = gifs[position]

            holder.bind(gif)

        }
    }






}

