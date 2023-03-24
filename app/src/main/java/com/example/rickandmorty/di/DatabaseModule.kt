package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.data.db.AppDatabase
import com.example.rickandmorty.data.db.RoomClient
import com.example.rickandmorty.data.db.daos.CharacterDao
import com.example.rickandmorty.data.db.daos.EpisodeDao
import com.example.rickandmorty.data.db.daos.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    val roomClient = RoomClient()

    @Singleton
    @Provides
    fun provideRoom(
        @ApplicationContext context: Context
    ): AppDatabase = roomClient.provideRoom(context)

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase
    ): CharacterDao = roomClient.provideCharacterDao(appDatabase)

    @Singleton
    @Provides
    fun provideEpisodeDao(appDatabase: AppDatabase
    ): EpisodeDao = roomClient.provideEpisodeDao(appDatabase)

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase
    ): LocationDao = roomClient.provideLocationDao(appDatabase)
}