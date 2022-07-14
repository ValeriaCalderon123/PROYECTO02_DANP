package com.example.proyecto02_danp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyecto02_danp.data.entity.Animal

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animals: List<Animal>)

    @Query(
        "SELECT * FROM animal WHERE commonName LIKE :queryString ORDER BY commonName DESC"
    )
    fun animalByName(queryString: String): PagingSource<Int, Animal>

    @Query("DELETE FROM animal")
    suspend fun clearAnimal()
}