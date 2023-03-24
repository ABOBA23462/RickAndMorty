package com.example.rickandmorty.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.models.LocationsModel

@Dao
interface LocationDao {

    @Query("SELECT * FROM locationsmodel")
    fun getAll(): LiveData<List<LocationsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(locationsModel: List<LocationsModel>)
}