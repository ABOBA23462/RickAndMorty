package com.example.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao
import com.example.rickandmorty.models.CharacterModel

@Database(entities = [CharacterModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}