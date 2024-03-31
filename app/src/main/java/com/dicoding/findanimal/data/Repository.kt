package com.dicoding.findanimal.data

import com.dicoding.findanimal.local.FavoriteAnimalDAO


class Repository(
    private val favoriteAnimalDao: FavoriteAnimalDAO,
) {

    private val animals = mutableListOf<Animal>()

    init {
        if (animals.isEmpty()) {
            AnimalData.dummyAnimals.forEach {
                animals.add(it)
            }
        }
    }

    fun getAnimals(): List<Animal> {
        return animals
    }

    fun searchAnimals(query: String): List<Animal> {
        return animals.filter {
            it.animalTitle.contains(query, ignoreCase = true)
        }
    }

    fun getAnimalById(animalId: Long): Animal {
        return animals.first {
            it.id == animalId
        }
    }

    fun saveAnimal(
        animalCoverURL: String,
        animalTitle: String,
        scientificName: String,
        origin: String,
        category: String,
        description: String
    ){
        val id = animals.last().id + 1
        animals.add(
            Animal(
                id,
                animalCoverURL,
                animalTitle,
                scientificName,
                origin,
                category,
                description
            )
        )
    }

    suspend fun saveFavoriteAnimal(favoriteAnimal: Animal) {
        favoriteAnimalDao.insert(favoriteAnimal)
    }

    suspend fun delete(favoriteAnimal: Animal) {
        favoriteAnimalDao.delete(favoriteAnimal)
    }

    suspend fun getFavoriteAnimal(): List<Animal> {
        return favoriteAnimalDao.getAllFavoriteUser()
    }

    fun isFavorite(animalId: Long): Boolean =
        favoriteAnimalDao.isFavorite(animalId)

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            favoriteAnimalDao: FavoriteAnimalDAO,
        ): Repository = instance ?: synchronized(this) {
            Repository(
                favoriteAnimalDao
            ).apply {
                instance = this
            }
        }
    }
}