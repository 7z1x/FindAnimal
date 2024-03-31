package com.dicoding.findanimal.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.findanimal.data.Animal


@Database(entities = [Animal::class], version = 1)
abstract class FavoriteAnimalDatabase: RoomDatabase() {

    abstract fun favoriteAnimalDao(): FavoriteAnimalDAO

    companion object {
        @Volatile
        private var INSTANCE: FavoriteAnimalDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteAnimalDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteAnimalDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteAnimalDatabase::class.java, "favorite_user_database"
                    )
                        .build()
                }
            }
            return INSTANCE as FavoriteAnimalDatabase
        }
    }
}