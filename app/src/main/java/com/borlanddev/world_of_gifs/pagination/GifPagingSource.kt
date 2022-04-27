package com.borlanddev.world_of_gifs.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borlanddev.world_of_gifs.model.Gif


typealias GifPageLoader = suspend (pageIndex: Int , pageSize: Int) -> List<Gif>


// Указываем источник данных для пагинации и аргументы пагинации (Key)
@Suppress("UnnecessaryVariable")
class GifPagingSource (
    private val loader: GifPageLoader,
    private val pageSize: Int
    ) : PagingSource<Int, Gif>() {


        // <Безопасно инициируем загрузку данных с помощью корутин
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {

            // Аргмуент пагинации
            val pageIndex = params.key ?: 0

            return try {

                // Загрузка данных с индексом страницы.
                // По умолчанию при первой загрузке в три раза больше даннных (loadSize)
                val response = loader.invoke (pageIndex, params.loadSize)

                // Загрузка успешна
                return LoadResult.Page(
                    data = response, // List<Gif>

                    // Возврат предыдущей страницы и последующей
                    prevKey = if (pageIndex == 0) null else pageIndex -1,
                    nextKey = if (response.size == params.loadSize) pageIndex + (params.loadSize/pageSize) else null)

            } catch (e: Exception) {

                LoadResult.Error (
                    throwable = e
                        )
            }
        }


    // Когда данные инвалидируются и их нужно перезагрузить, или пользователь об новляет данные.
    // Происходит новое вычисление аргументов пагинации, вернется Индекс страницы для новой загрузки.
        override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {

            // Вернет индекс страницы
            return state.anchorPosition?.let { anchorPosition ->

                // Вычисляем ближайшую актуальную страницу с данными
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }
    }

