package com.example.rickandmorty.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.converter.Converters
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field
import java.util.Objects

@Entity
data class CharacterModel(

    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("location")
    @field:TypeConverters(Converters::class)
    val location: Objects
)