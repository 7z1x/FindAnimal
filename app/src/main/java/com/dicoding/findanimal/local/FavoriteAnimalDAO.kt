package com.dicoding.findanimal.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dicoding.findanimal.data.Animal

@Dao
interface FavoriteAnimalDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteAnimal: Animal)

    @Update
    suspend fun update(favoriteUser: Animal)

    @Delete
    suspend fun delete(favoriteUser: Animal)

    @Query("SELECT * from Animal")
    suspend fun getAllFavoriteUser(): List<Animal>

    @Query("SELECT EXISTS(SELECT * FROM Animal WHERE Animal.id = :animalId)")
    fun isFavorite(animalId: Long): Boolean
}