package com.dicoding.findanimal.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "animal_cover_url")
    var animalCoverURL: String,

    @ColumnInfo(name = "animal_title")
    var animalTitle: String,

    @ColumnInfo(name = "scientific_name")
    var scientificName: String,

    @ColumnInfo(name = "origin")
    var origin: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "description")
    var description: String
)