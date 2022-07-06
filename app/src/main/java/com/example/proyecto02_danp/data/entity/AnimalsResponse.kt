package com.example.lab05_room.data.entity

import com.google.gson.annotations.SerializedName

data class AnimalsResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("result") val list_animals: List<Animals>
)


