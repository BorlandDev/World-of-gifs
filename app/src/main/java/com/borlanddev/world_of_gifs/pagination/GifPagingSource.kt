package com.borlanddev.world_of_gifs.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.borlanddev.world_of_gifs.model.Gif

typealias GifPageLoader = suspend (pageIndex: Int , pageSize: Int) -> List<Gif>


@Suppress("UnnecessaryVariable")
class GifPagingSource (
    private val loader: GifPageLoader,
    private val pageSize: Int
    ) : PagingSource<Int, Gif>() {


        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {

            val pageIndex = params.key ?: 0

            return try {

                // Загрузка данных
                val response = loader.invoke (pageIndex, params.loadSize)

                return LoadResult.Page(
                    data = response,
                    prevKey = if (pageIndex == 0) null else pageIndex -1,
                    nextKey = if (response.size == params.loadSize) pageIndex + (params.loadSize/pageSize) else null
                )
            } catch (e: Exception) {

                LoadResult.Error (
                    throwable = e
                        )
            }
        }


    // Когда данные инвалидируются и их нужно перезагрузить, или пользователь об новляет данные.
        override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {

            // Вернет индекс страницы
            return state.anchorPosition?.let { anchorPosition ->

                // Вычисляем ближайшую актуальную страницу с данными
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }
    }

