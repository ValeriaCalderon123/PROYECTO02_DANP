package com.example.proyecto02_danp

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lab05_room.data.entity.Animals
import com.example.lab05_room.data.entity.AnimalsResponse
import com.example.proyecto02_danp.data.APIService
import java.io.IOException

class AnimalsPagingSource(private val api: APIService) : PagingSource<Int, Animals>() {
    override val keyReuseSupported: Boolean
        get() = super.keyReuseSupported

    override fun getRefreshKey(state: PagingState<Int, Animals>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Animals> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: AnimalsResponse = api.getAnimals()

            val listAnimals = arrayListOf<Animals>()
            var i = 0
            while (listAnimals.size < 21) {
                if (response.list_animals[i].main_common_name !== null) {
                    val animal = Animals(
                        response.list_animals[i].taxonid,
                        response.list_animals[i].kingdom_name,
                        response.list_animals[i].phylum_name,
                        response.list_animals[i].class_name,
                        response.list_animals[i].order_name,
                        response.list_animals[i].family_name,
                        response.list_animals[i].genus_name,
                        response.list_animals[i].scientific_name,
                        response.list_animals[i].taxonomic_authority,
                        response.list_animals[i].infra_rank,
                        response.list_animals[i].infra_name,
                        response.list_animals[i].population,
                        response.list_animals[i].category,
                        response.list_animals[i].main_common_name,
                    )
                    listAnimals.add(animal)
                }
                i += 1
            }

            Log.e("DATA: ", listAnimals.toString())

            return LoadResult.Page(
                data = listAnimals,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 10) nextPageNumber + 1 else null
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

}
