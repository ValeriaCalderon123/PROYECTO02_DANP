package com.example.lab05_room.data.entity

import com.example.proyecto02_danp.data.entity.Animals
import com.google.gson.annotations.SerializedName

data class AnimalsResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("result") val list_animals: List<Animals>
)


