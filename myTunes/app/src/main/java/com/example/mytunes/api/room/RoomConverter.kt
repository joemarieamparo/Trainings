package com.example.mytunes.api.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * RoomConverter's purpose is to convert the collection list
 * to be able to successfully save to the database and convert it back
 * as list during fetching
 */
class RoomConverter {

    @TypeConverter
    fun fromString(string: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {
        }.type
        val value = if(!string.isNullOrEmpty()) string else ""
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        val value = if (!list.isNullOrEmpty()) list else emptyList()
        return Gson().toJson(value)
    }
}