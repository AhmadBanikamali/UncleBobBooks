package com.abcdandroid.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun toArrayList(list:String):  List<String?>? {
        val gson = Gson()
        return gson.fromJson(list,  object : TypeToken<List<String?>>() {}.type)
    }
}