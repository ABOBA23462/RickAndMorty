package com.example.rickandmorty.data.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao

class RoomClient {

    fun provideRoom(context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "rick")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase
        .characterDao()

    fun provideEpisodeDao(appDatabase: AppDatabase): EpisodeDao = appDatabase
        .episodeDao()

    fun provideLocationDao(appDatabase: AppDatabase): LocationDao = appDatabase
        .locationDao()
}