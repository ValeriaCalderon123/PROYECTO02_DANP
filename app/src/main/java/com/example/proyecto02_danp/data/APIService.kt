package com.example.proyecto02_danp.data

import com.example.lab05_room.data.entity.AnimalsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("api/v3/species/page/0?token=9bb4facb6d23f48efbf424bb05c0c1ef1cf6f468393bc745d42179ac4aca5fee")
    suspend fun getAnimals(): AnimalsResponse

    companion object {

        private const val BASE_URL = "https://apiv3.iucnredlist.org/"

        operator fun invoke(): APIService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}

