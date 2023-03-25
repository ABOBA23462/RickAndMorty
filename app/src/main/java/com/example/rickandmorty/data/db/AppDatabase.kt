package com.example.rickandmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.converter.Converters
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao
import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.models.EpisodeModel
import com.example.rickandmorty.models.LocationsModel

@Database(entities = [CharacterModel::class, EpisodeModel::class, LocationsModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
}