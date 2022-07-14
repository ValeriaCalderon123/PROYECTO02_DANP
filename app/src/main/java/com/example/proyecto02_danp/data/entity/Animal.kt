package com.example.proyecto02_danp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "animal")
data class Animal(
    @PrimaryKey @field:SerializedName("id") val idAnimal: String,
    @field:SerializedName("characteristic_1") val characteristic1: String,
    @field:SerializedName("characteristic_2") val characteristic2: String,
    @field:SerializedName("class_name") val className: String,
    @field:SerializedName("common_name") val commonName :String,
    @field:SerializedName("family_name") val familyName: String,
    @field:SerializedName("habitat") val habitat :String,
    @field:SerializedName("length") val length :String,
    @field:SerializedName("photo_url") val photoUrl :String,
    @field:SerializedName("scientific_name") val scientificName :String,
    @field:SerializedName("state_animal") val state_animal :String,
    @field:SerializedName("weight") val weight :String
)
