package com.example.proyecto02_danp.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.proyecto02_danp.data.entity.Animal
import com.example.proyecto02_danp.data.entity.Animals
import com.example.proyecto02_danp.data.entity.RemoteKeys
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AnimalRemoteMediator(
   private val animalDatabase: AnimalDatabase
): RemoteMediator<Int, Animal>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Animal>): MediatorResult {
        val db = Firebase.firestore
        try {
            val animals = ArrayList<Animal>()
            db.collection("endangered_animals")
                .get()
                .addOnSuccessListener { result ->
                    for(animal in result) {
                        animals.add(Animal(
                            animal.id,
                            animal.data["characteristic_1"].toString(),
                            animal.data["characteristic_2"].toString(),
                            animal.data["class_name"].toString(),
                            animal.data["common_name"].toString(),
                            animal.data["family_name"].toString(),
                            animal.data["habitat"].toString(),
                            animal.data["length"].toString(),
                            animal.data["photo_url"].toString(),
                            animal.data["scientific_name"].toString(),
                            animal.data["state_animal"].toString(),
                            animal.data["weight"].toString(),
                        ))
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore-ERROR", "Error getting documents.", exception)
                }
            val endOfPaginationReached = animals.isEmpty()
            animalDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    animalDatabase.remoteKeysDao().clearRemoteKeys()
                    animalDatabase.animalDao().clearAnimal()
                }
                val prevKey = if (animals.size == 1) null else 0
                val nextKey = if (endOfPaginationReached) null else prevKey?.plus(2)
                val keys = animals.map {
                    RemoteKeys(animalId = it.idAnimal, prevKey = prevKey, nextKey = nextKey)
                }
                animalDatabase.remoteKeysDao().insertAll(keys)
                animalDatabase.animalDao().insertAll(animals)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}