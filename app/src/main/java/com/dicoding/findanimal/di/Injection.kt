package com.dicoding.findanimal.di

import android.content.Context
import com.dicoding.findanimal.data.Repository
import com.dicoding.findanimal.local.FavoriteAnimalDatabase

object Injection {
    fun provideRepository(context: Context): Repository {
        val database = FavoriteAnimalDatabase.getDatabase(context)
        val dao = database.favoriteAnimalDao()
        return Repository.getInstance(dao)
    }
}