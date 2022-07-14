package com.example.proyecto02_danp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto02_danp.data.dao.AnimalDao
import com.example.proyecto02_danp.data.dao.RemoteKeysDao
import com.example.proyecto02_danp.data.entity.Animal
import com.example.proyecto02_danp.data.entity.RemoteKeys

@Database(
    entities = [Animal::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getInstance(context: Context): AnimalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AnimalDatabase::class.java, "Animal.db"
            )
                .build()
    }

}