package com.example.rickandmorty.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class Converters {
    @TypeConverter
fun fromObject(objects: Objects): String{
    return Gson().toJson(objects)
    }

    @TypeConverter
    fun toObject(string: String): Objects{
        return Gson().fromJson(string, Objects::class.java)
    }
}