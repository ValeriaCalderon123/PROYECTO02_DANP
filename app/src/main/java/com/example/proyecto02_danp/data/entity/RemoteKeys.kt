package com.example.proyecto02_danp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val animalId: String,
    val prevKey: Int?,
    val nextKey: Int?
)